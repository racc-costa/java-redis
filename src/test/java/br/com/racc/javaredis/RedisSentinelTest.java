package br.com.racc.javaredis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RedisSentinelTest {
	private static final String SENTINEL_HOST = "localhost";
	private static final int SENTINEL1_PORT = 6390;
	private static final int SENTINEL2_PORT = 6391;
	private static final int SENTINEL3_PORT = 6392;
	private static final String KEY_1 = "1";
	private static final String KEY_2 = "2";
	private static final String VALUE_1 = "Value 1";
	private static final String VALUE_2 = "Value 2";
	private static final String PING_RESPONSE = "PONG";

	private RedisSentinel redis;

	@Before
	public void connect() {
		Set<String> sentinels = new HashSet<String>();
		sentinels.add(SENTINEL_HOST + ":" + SENTINEL1_PORT);
		sentinels.add(SENTINEL_HOST + ":" + SENTINEL2_PORT);
		sentinels.add(SENTINEL_HOST + ":" + SENTINEL3_PORT);
		redis = new RedisSentinel("redis", sentinels, 5000);
	}

	@After
	public void disconnect() {
		redis.disconnect();
	}

	@Test(timeout = 5)
	public void testIsConnected() {
		assertTrue(redis.isConnected());
	}

	@Test(timeout = 20)
	public void testPing() {
		assertEquals(PING_RESPONSE, redis.ping());
	}

	@Test(timeout = 5)
	public void testGetSet() {
		redis.set(KEY_1, VALUE_1);
		redis.set(KEY_2, VALUE_2);

		assertEquals(VALUE_1, redis.get(KEY_1));
		assertEquals(VALUE_2, redis.get(KEY_2));
	}

	@Test(timeout = 10)
	public void testGetInfo() {
		System.out.println(redis.getInfo());
	}

	@Test(timeout = 5)
	public void testGetServerInfo() {
		System.out.println(redis.getServerInfo());
	}

	@Test
	public void testFailOver() throws InterruptedException {
		for (int i = 0; i <= 600; i++) {
			Thread.sleep(1000);
			try {
				redis.set("key" + i, "value" + i);
				String value = redis.get("key" + i);
				System.out.println("Key in redis: " + value);
			} catch (Throwable e) {
				redis.renewMaster();
				System.err.println("Key not in redis: " + i);
			}
		}
	}
}