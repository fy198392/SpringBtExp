package com.shinefy.project.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.shinefy.project.entity.TestTable;
import com.shinefy.project.util.RedisObjectSerializer;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
public class JRedisConfiguration {
		@Value("${spring.redis.host}")
	    private String host;

	    @Value("${spring.redis.port}")
	    private int port;

	    @Value("${spring.redis.timeout}")
	    private int timeout;

	    @Value("${spring.redis.pool.max-idle}")
	    private int maxIdle;

	    @Value("${spring.redis.pool.max-wait}")
	    private long maxWaitMillis;

	    @Value("${spring.redis.password}")
	    private String password;
	    @Value("${spring.redis.pool.min-idle}")
	    private int minIdele;
	
	    @Value("${spring.redis.pool.max-active}")
	    private int maxActive;
	    private static final Logger logger = LoggerFactory.getLogger(JRedisConfiguration.class);
	 @Bean
	    public JedisPool redisPoolFactory() {
	        logger.info("JedisPool注入成功！！");
	        logger.info("redis地址：" + host + ":" + port);
	        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
	        jedisPoolConfig.setMaxIdle(maxIdle);
	        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
	        jedisPoolConfig.setMinIdle(minIdele);
	        jedisPoolConfig.setMaxTotal(maxActive);
	        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);

	        JedisConnectionFactory factory = new JedisConnectionFactory();
	        factory.setPoolConfig(jedisPoolConfig);
	        return jedisPool;
	    }
	
	
}
