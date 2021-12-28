package com.sofn.core.support.cache.jedis;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 使用jedisCluster操作redis集群
 * @author wangzg
 * @date 2016.12.16
 */
public class JedisClusterFactory implements FactoryBean<JedisCluster>, InitializingBean {

//   使用方式如下，jedisCluster就是对应的bean id ，可以自动注入
//	@Autowired
//	private JedisCluster jedisCluster;

	//private GenericObjectPoolConfig genericObjectPoolConfig;
	private JedisPoolConfig jedisPoolConfig;
	private JedisCluster jedisCluster;
	private Resource addressConfig;
	private String addressKeyPrefix;

	private RedisClusterConfiguration clusterConfig;
	private int timeout = 3000;
	private int connectionTimeout = 2000;
	private int maxRedirections = 5;
	private Set<String> jedisClusterNodes;

	private Pattern p = Pattern.compile("^.+[:]\\d{1,5}\\s*$");

	private Set<HostAndPort> parseHostAndPort() throws Exception {
		try {
			Properties prop = new Properties();
			prop.load(this.addressConfig.getInputStream());
			//jedisClusterNodes
//			Set<RedisNode> nodesConfig = clusterConfig.getClusterNodes();
			Set<HostAndPort> haps = new HashSet<HostAndPort>();
			for (Object key : prop.keySet()) {
				if (!((String) key).startsWith(addressKeyPrefix)) {
					continue;
				}
				String val = (String) prop.get(key);
				boolean isIpPort = p.matcher(val).matches();
				if (!isIpPort) {
					throw new IllegalArgumentException("ip 或 port 不合法");
				}
				String[] ipAndPort = val.split(":");
				HostAndPort hap = new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
				haps.add(hap);
			}
			return haps;
//			JedisCluster jc = new JedisCluster(jedisClusterNodes);
//			jc.set("foo_first", "bar");
//			String value = jc.get("foo_first");
//			System.out.println(value);
		} catch (IllegalArgumentException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new Exception("解析 jedis 配置文件失败", ex);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (jedisClusterNodes == null || jedisClusterNodes.size() == 0) {
			throw new NullPointerException("jedisClusterNodes is null.");
		}
		/*Set<HostAndPort> haps = new HashSet<HostAndPort>();
		for (String node : jedisClusterNodes) {
			String[] arr = node.split(":");
			if (arr.length != 2) {
				throw new ParseException("node address error !",node.length()-1);
			}
			haps.add(new HostAndPort(arr[0], Integer.valueOf(arr[1])));
		}*/
		Set<HostAndPort> haps = this.parseHostAndPort();
		/*jedisCluster = new JedisCluster(haps, connectionTimeout, timeout, maxRedirections, genericObjectPoolConfig);*/
		jedisCluster = new JedisCluster(haps, connectionTimeout, timeout, maxRedirections, jedisPoolConfig);
	}

	@Override
	public JedisCluster getObject() throws Exception {
		return jedisCluster;
	}

	@Override
	public Class<?> getObjectType() {
		return (this.jedisCluster != null ? this.jedisCluster.getClass() : JedisCluster.class);
	}

	/*@Override
	public Class<? extends JedisCluster> getObjectType() {
		return (this.jedisCluster != null ? this.jedisCluster.getClass() : JedisCluster.class);
	}*/

	@Override
	public boolean isSingleton() {
		return true;
	}

/*	public GenericObjectPoolConfig getGenericObjectPoolConfig() {
		return genericObjectPoolConfig;
	}

	public void setGenericObjectPoolConfig(GenericObjectPoolConfig genericObjectPoolConfig) {
		this.genericObjectPoolConfig = genericObjectPoolConfig;
	}*/

	public JedisPoolConfig getJedisPoolConfig() {
		return jedisPoolConfig;
	}

	public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
		this.jedisPoolConfig = jedisPoolConfig;
	}

	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setMaxRedirections(int maxRedirections) {
		this.maxRedirections = maxRedirections;
	}

	public void setAddressConfig(Resource addressConfig) {
		this.addressConfig = addressConfig;
	}

	public void setAddressKeyPrefix(String addressKeyPrefix) {
		this.addressKeyPrefix = addressKeyPrefix;
	}

	public void setClusterConfig(RedisClusterConfiguration clusterConfig) {
		this.clusterConfig = clusterConfig;
	}

	public Set<String> getJedisClusterNodes() {
		return jedisClusterNodes;
	}

	public void setJedisClusterNodes(Set<String> jedisClusterNodes) {
		this.jedisClusterNodes = jedisClusterNodes;
	}
}