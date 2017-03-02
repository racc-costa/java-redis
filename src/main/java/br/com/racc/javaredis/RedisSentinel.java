package br.com.racc.javaredis;

import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.JedisSentinelPool;

public class RedisSentinel extends Redis {
	private JedisSentinelPool pool;

	public RedisSentinel(String masterName, Set<String> sentinels, int timeout) {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		pool = new JedisSentinelPool(masterName, sentinels, config, 5000);
		setJedis(pool.getResource());
	}

	public void renewMaster() {
		try {
			setJedis(pool.getResource());
		} catch (Throwable e) {

		}
	}

	public void disconnect() {
		getJedis().close();
		pool.close();
		pool.destroy();
	}
}
