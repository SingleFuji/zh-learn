package com.zh.cache.blog_demo;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zh.cache.redis.RedisStringService;

@Service("blogRedisDemoService")
public class BlogRedisDemoService {

	@Resource
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	
	public void putString(String sessionId, String body) {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set(redisTemplate.getStringSerializer().serialize("good"), redisTemplate.getStringSerializer().serialize("friend"));
	}

	public void incrNum()
	{
//		redisTemplate.opsForValue().increment(key, delta)
	}
	
}
