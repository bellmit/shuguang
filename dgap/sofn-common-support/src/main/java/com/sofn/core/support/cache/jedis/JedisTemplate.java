package com.sofn.core.support.cache.jedis;

import com.sofn.core.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

/**
 * jedis 集群模式 jedisCluster（redis cluster since redis3.x，redis服务器集群，采用主从模式）,基于 JedisCluster
 * jedis 单台模式 jedis
 * jedis 分片模式 shardedJedis（客户端集群，redis服务器分片），基于ShardedJedis
 * @author sofn
 * @version 2016年5月20日 下午3:19:19
 */
public class JedisTemplate {
	private static final Logger logger = LoggerFactory.getLogger(JedisTemplate.class);
	//非切片模式
	/*private Jedis jedis;//非切片额客户端连接
	private JedisPool jedisPool;//非切片连接池
	private static JedisPool jedisPool = null;
	private static Integer EXPIRE = PropertiesUtil.getInt("redis.expiration");
	private  static Jedis getJedis(){
		if (jedisPool == null) {
			synchronized (EXPIRE) {
				if (jedisPool == null) {
					WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
					jedisPool = wac.getBean(JedisPool.class);
				}
			}
		}
		return jedisPool.getResource();
	}

	public static <K> K run(String key, Executor<K> executor, Integer... expire) {
		Jedis jedis = getJedis();
		if (jedis == null) {
			return null;
		}
		try {
			K result = executor.execute(jedis);
			if (jedis.exists(key)) {
				if (expire == null || expire.length == 0) {
					jedis.expire(key, EXPIRE);
				} else if (expire.length == 1) {
					jedis.expire(key, expire[0]);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static <K> K run(byte[] key, Executor<K> executor, Integer... expire) {
		Jedis jedis = getJedis();
		if (jedis == null) {
			return null;
		}
		try {
			K result = executor.execute(jedis);
			if (jedis.exists(key)) {
				if (expire == null || expire.length == 0) {
					jedis.expire(key, EXPIRE);
				} else if (expire.length == 1) {
					jedis.expire(key, expire[0]);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}
	*/

	//集群模式
    //以下采用服务器集群模式
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/config/redis.xml");
	JedisCluster jedisClient = context.getBean(JedisCluster.class);

	private static JedisCluster jedisCluster;
	@Autowired
	public void setJedisCluster(JedisCluster jedisCluster) {
		JedisTemplate.jedisCluster = jedisCluster;
	}
    //默认过期时间
	/*private static Integer EXPIRE = PropertiesUtil.getInt("redis.expiration");

	public static <K> K run(String key, Executor<K> executor, Integer... expire) {
		if (jedisCluster == null) {
			return null;
		}
		try {
			K result = executor.execute(jedisCluster);
			if (jedisCluster.exists(key)) {
				if (expire == null || expire.length == 0) {
					jedisCluster.expire(key, EXPIRE);
				} else if (expire.length == 1) {
					jedisCluster.expire(key, expire[0]);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedisCluster != null) {

			}
		}
		return null;
	}

	public static <K> K run(byte[] key, Executor<K> executor, Integer... expire) {
		if (jedisCluster == null) {
			return null;
		}
		try {
			K result = executor.execute(jedisCluster);
			if (jedisCluster.exists(key)) {
				if (expire == null || expire.length == 0) {
					jedisCluster.expire(key, EXPIRE);
				} else if (expire.length == 1) {
					jedisCluster.expire(key, expire[0]);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedisCluster != null) {
			}
		}
		return null;
	}*/


	//以下采用客户端集群模式，即服务器分片
	//分片模式
	/*private ShardedJedis shardedJedis;//切片额客户端连接
	private ShardedJedisPool shardedJedisPool;//切片连接池*/
	/*private static ShardedJedisPool shardedJedisPool = null;
	//redis过期时间设置
	private static Integer EXPIRE = PropertiesUtil.getInt("redis.expiration");
	// 获取线程
	private static ShardedJedis getJedis() {
		if (shardedJedisPool == null) {
			synchronized (EXPIRE) {
				if (shardedJedisPool == null) {
					WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
					shardedJedisPool = wac.getBean(ShardedJedisPool.class);
				}
			}
		}
		return shardedJedisPool.getResource();
	}

	public static <K> K run(String key, Executor<K> executor, Integer... expire) {
		ShardedJedis jedis = getJedis();
		if (jedis == null) {
			return null;
		}
		try {
			K result = executor.execute(jedis);
			if (jedis.exists(key)) {
				if (expire == null || expire.length == 0) {
					jedis.expire(key, EXPIRE);
				} else if (expire.length == 1) {
					jedis.expire(key, expire[0]);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}

	public static <K> K run(byte[] key, Executor<K> executor, Integer... expire) {
		ShardedJedis jedis = getJedis();
		if (jedis == null) {
			return null;
		}
		try {
			K result = executor.execute(jedis);
			if (jedis.exists(key)) {
				if (expire == null || expire.length == 0) {
					jedis.expire(key, EXPIRE);
				} else if (expire.length == 1) {
					jedis.expire(key, expire[0]);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return null;
	}*/
}
