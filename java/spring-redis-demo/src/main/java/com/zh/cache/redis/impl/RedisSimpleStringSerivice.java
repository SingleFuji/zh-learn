package com.zh.cache.redis.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zh.cache.redis.RedisStringService;

@Service("redisSimpleStringSerivice")
public class RedisSimpleStringSerivice implements RedisStringService {

	@Resource
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	
	@Override
	public void putString(String sessionId, String body) {
		// TODO Auto-generated method stub
		redisTemplate.opsForValue().set(redisTemplate.getStringSerializer().serialize("good"), redisTemplate.getStringSerializer().serialize("friend"));
	}

	@Override
	public void putString(String sessionId, String body, long seconds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getString(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteString(String sessionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean setNX(String sessionId, String body, long seconds) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
