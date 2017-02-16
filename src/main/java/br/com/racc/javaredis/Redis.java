package br.com.racc.javaredis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class Redis {
	private Set<HostAndPort> nosCluster;
	private JedisCluster cluster;
	Jedis servidor; 

	public Redis(String host , int porta) {
		servidor = new Jedis(host, porta);
	}
	
	public Redis(List<HostAndPort> nodes) {
		nosCluster = new HashSet<HostAndPort>();
		for (HostAndPort node : nodes) {
			nosCluster.add(node);
		}

		cluster = new JedisCluster(nosCluster);
	}
	
	public JedisCluster getCluster() {
		return cluster; 
	}
	
	public Jedis getServidor() {
		return servidor; 
	}
}
