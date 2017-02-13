package br.com.racc.javaredis;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class RedisTest {

	@Test
	public void testRedisServidor() {
		Jedis servidor = new Redis("localhost", 6379).getServidor();
		assertNotNull(servidor);
	}
	
	@Test
	public void testAddServidor() throws InterruptedException {
		Jedis servidor = new Redis("172.25.1.166", 6379).getServidor();
		servidor.setex("1", 2, "Ricardo Costa");
		String nome = servidor.get("1");
		assertEquals("Ricardo Costa", nome);
		Thread.sleep(3000);
		nome = servidor.get("1");
		assertNull(nome);

	}

	public void testRedisCluster() {
		JedisCluster cluster;
		List<HostAndPort> nodes = new ArrayList<HostAndPort>();
		nodes.add(new HostAndPort("localhost", 6379));
		nodes.add(new HostAndPort("localhost", 6380));
		cluster = new Redis(nodes).getCluster();

		assertNotNull(cluster);
	}

	public void testSet() {
		JedisCluster cluster;
		List<HostAndPort> nodes = new ArrayList<HostAndPort>();
		nodes.add(new HostAndPort("localhost", 6379));
		nodes.add(new HostAndPort("localhost", 6380));
		cluster = new Redis(nodes).getCluster();
		cluster.set("1", "Ricardo Costa");
		String nome = cluster.get("1");

		assertEquals("Ricardo Costa", nome);
	}
}
