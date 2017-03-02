package br.com.racc.javaredis;

import redis.clients.jedis.Jedis;

public class RedisStandAlone extends Redis {

	public RedisStandAlone(String host, int port) {
		setJedis(new Jedis(host, port));
		getJedis().connect();
	}
}
