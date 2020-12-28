package com.jufo.spring_test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jufo.app.Application;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {Application.class})
public class RedisTest {
	
	@Autowired
	JedisPool jedisPool;
	
	@Test
	public void simpleTest() {
//		Jedis jedis = new Jedis("192.168.122.1", 6379);
		Jedis jedis = new Jedis("192.168.182.131", 6379);
		String v = jedis.get("zhcf:k1");
		System.out.println(v);
		jedis.close();
	}

	@Test
	public void redisTest1() {
		try(Jedis jedis = jedisPool.getResource()){
			jedis.select(0);
			String v = jedis.get("zhcf:k1");
			System.out.println(v);
			
		}
	}
}
