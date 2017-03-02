package br.com.racc.javaredis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RedisStandAloneTest {

	private static final String REDIS_HOST = "localhost";
	private static final int REDIS_PORT = 6379;
	private static final String KEY_1 = "1";
	private static final String KEY_2 = "2";
	private static final String VALUE_1 = "Value 1";
	private static final String VALUE_2 = "Value 2";
	private static final String PING_RESPONSE = "PONG";

	private RedisStandAlone redis;

	@Before
	public void connect() {
		redis = new RedisStandAlone(REDIS_HOST, REDIS_PORT);
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
}
