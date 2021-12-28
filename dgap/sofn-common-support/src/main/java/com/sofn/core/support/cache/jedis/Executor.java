package com.sofn.core.support.cache.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;
import redis.clients.util.Sharded;

/**
 * @author sofn
 * @version 2016年5月20日 下午3:19:19
 */
public interface Executor<K> {
	/** 集群模式 */
	public K execute(JedisCluster jedis);
	/** 分片模式 */
//	public K execute(ShardedJedis jedis);
	/** 单机模式 */
//	public K execute(Jedis jedis);
}
