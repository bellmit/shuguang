## redis缓存配置 wangzg 2016.12.26 
<!-- JedisCluster -->
<bean id="jedisCluster" class="redis.clients.jedis.JedisCluster">
		<constructor-arg name="node">
			<set>
				<bean class="redis.clients.jedis.HostAndPort">
					<constructor-arg index="0" value="192.168.1.111"/>
					<constructor-arg index="1" value="7111" />
				</bean>
				<bean class="redis.clients.jedis.HostAndPort">
					<constructor-arg index="0" value="192.168.1.112"/>
					<constructor-arg index="1" value="7112" />
				</bean>
				<bean class="redis.clients.jedis.HostAndPort">
					<constructor-arg index="0" value="192.168.1.113"/>
					<constructor-arg index="1" value="7113" />
				</bean>
				<bean class="redis.clients.jedis.HostAndPort">
					<constructor-arg index="0" value="192.168.1.114"/>
					<constructor-arg index="1" value="7114" />
				</bean>
				<bean class="redis.clients.jedis.HostAndPort">
					<constructor-arg index="0" value="192.168.1.115"/>
					<constructor-arg index="1" value="7115" />
				</bean>
				<bean class="redis.clients.jedis.HostAndPort">
					<constructor-arg index="0" value="192.168.1.116"/>
					<constructor-arg index="1" value="7116" />
				</bean>
			</set>
		</constructor-arg>
		<constructor-arg name="timeout" value="2000" ></constructor-arg>
		<constructor-arg name="maxAttempts" value="100" ></constructor-arg>
		<constructor-arg name="poolConfig" ref="jedisPoolConfig"></constructor-arg>
</bean>
<!-- 调用方法 -->
JedisCluster jedisCluster = (JedisCluster)context.getBean("jedisCluster");
// 存数据
jedisCluster.set(key+i,"WuShuicheng"+i);
// 取数据
value= jedisCluster.get(key+i);
// 删除数据
//jedisCluster.del(key+i);