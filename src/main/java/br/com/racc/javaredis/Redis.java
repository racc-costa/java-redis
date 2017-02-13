package br.com.racc.javaredis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisCluster {
	private Set<HostAndPort> clusterNodes;
	private JedisCluster cluster;

	public RedisCluster(List<HostAndPort> nodes) {
		clusterNodes = new HashSet<HostAndPort>();
		for (HostAndPort node : nodes) {
			clusterNodes.add(node);
		}

		cluster = new JedisCluster(clusterNodes);
	}
	
	public JedisCluster getCluster() {
		return cluster; 
	}
}
