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
	private static final int SENTINEL1_PORT = 26379;
	private static final int SENTINEL2_PORT = 26380;
	private static final int SENTINEL3_PORT = 26381;
	private static final String KEY_1 = "1";
	private static final String KEY_2 = "2";
	private static final String VALUE_1 = "Value 1";
	private static final String VALUE_2 = "Value 2";
	private static final String PING_RESPONSE = "PONG";
	
	private RedisSentinel sentinel;

	@Before
	public void connect() {
		Set<String> sentinels = new HashSet<String>();
		sentinels.add(SENTINEL_HOST + ":" + SENTINEL1_PORT);
		sentinels.add(SENTINEL_HOST + ":" + SENTINEL2_PORT);
		sentinels.add(SENTINEL_HOST + ":" + SENTINEL3_PORT);
		sentinel = new RedisSentinel("redis", sentinels, 5000);
	}
	
	@After
	public void disconnect() {
		sentinel.disconnect();
	}


	@Test(timeout = 5)
	public void testIsConnected() {
		assertTrue(sentinel.isConnected());
	}

	@Test(timeout = 20)
	public void testPing() {
		assertEquals(PING_RESPONSE, sentinel.ping());
	}

	@Test(timeout = 5)
	public void testGetSet() {
		sentinel.set(KEY_1, VALUE_1);
		sentinel.set(KEY_2, VALUE_2);

		assertEquals(VALUE_1, sentinel.get(KEY_1));
		assertEquals(VALUE_2, sentinel.get(KEY_2));
	}

	@Test(timeout = 10)
	public void testGetInfo() {
		System.out.println(sentinel.getInfo());
	}

	@Test(timeout = 5)
	public void testGetServerInfo() {
		System.out.println(sentinel.getServerInfo());
	}
}
