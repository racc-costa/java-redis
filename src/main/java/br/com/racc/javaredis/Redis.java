package br.com.racc.javaredis;

import java.util.LinkedHashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class Redis {
	private Jedis jedis;

	protected Jedis getJedis() {
		return this.jedis;
	}

	protected void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}

	public boolean isConnected() {
		return jedis.isConnected();
	}

	public String ping() {
		return jedis.ping();
	}

	public void set(String key, String value) {
		jedis.set(key, value);
	}

	public String get(String key) {
		return jedis.get(key);
	}

	public Map<String, String> getInfo() {
		String info = jedis.info();
		return splitInfo(info);
	}

	public Map<String, String> getServerInfo() {
		String info = jedis.info("Server");
		return splitInfo(info);
	}

	public Map<String, String> getClientsInfo() {
		String info = jedis.info("Clients");
		return splitInfo(info);
	}

	public Map<String, String> getMemoryInfo() {
		String info = jedis.info("Memory");
		return splitInfo(info);
	}

	public Map<String, String> getPersistenceInfo() {
		String info = jedis.info("Persistence");
		return splitInfo(info);
	}

	public Map<String, String> getStatsInfo() {
		String info = jedis.info("Stats");
		return splitInfo(info);
	}

	public Map<String, String> getReplicationInfo() {
		String info = jedis.info("Replication");
		return splitInfo(info);
	}

	public Map<String, String> getCPUInfo() {
		String info = jedis.info("CPU");
		return splitInfo(info);
	}

	public Map<String, String> getClusterInfo() {
		String info = jedis.info("Cluster");
		return splitInfo(info);
	}

	public Map<String, String> getKeyspaceInfo() {
		String info = jedis.info("Keyspace");
		return splitInfo(info);
	}

	private Map<String, String> splitInfo(String info) {
		Map<String, String> infoMap = new LinkedHashMap<String, String>();
		String[] lines = info.split("\\r?\\n");
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains(":")) {
				String[] line = lines[i].split(":");
				infoMap.put(line[0], line[1]);
			}
		}
		return infoMap;
	}
}