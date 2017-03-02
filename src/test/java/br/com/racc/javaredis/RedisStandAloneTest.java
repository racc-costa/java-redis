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

	private RedisStandAlone standAlone;

	@Before
	public void connect() {
		standAlone = new RedisStandAlone(REDIS_HOST, REDIS_PORT);
	}

	@Test(timeout = 5)
	public void testIsConnected() {
		assertTrue(standAlone.isConnected());
	}

	@Test(timeout = 20)
	public void testPing() {
		assertEquals(PING_RESPONSE, standAlone.ping());
	}

	@Test(timeout = 5)
	public void testGetSet() {
		standAlone.set(KEY_1, VALUE_1);
		standAlone.set(KEY_2, VALUE_2);

		assertEquals(VALUE_1, standAlone.get(KEY_1));
		assertEquals(VALUE_2, standAlone.get(KEY_2));
	}

	@Test(timeout = 10)
	public void testGetInfo() {
		System.out.println(standAlone.getInfo());
	}

	@Test(timeout = 5)
	public void testGetServerInfo() {
		System.out.println(standAlone.getServerInfo());
	}
}
