package com.zh.cache.redis.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zh.cache.redis.CacheKey;
import com.zh.cache.redis.RedisObjectService;

@Service("redisObjectService")
public class RedisObjectServiceImpl implements RedisObjectService
{
	
	private static final String ZH_TAG = "zh.test.";//com.xinguodu.prepaidcard.object.
	
	@SuppressWarnings("rawtypes")
	@Resource
	private RedisTemplate redisTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public void putObject(final String key, final Object obj)
	{
//		logger.info("putObject data:" + obj.toString());
		redisTemplate.execute(new RedisCallback<Object>()
		{
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException
			{
				byte[] btyKey = redisTemplate.getStringSerializer().serialize(ZH_TAG + key);
				Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
				map.put(redisTemplate.getStringSerializer().serialize("obj"), redisTemplate.getDefaultSerializer().serialize(obj));
				connection.hMSet(btyKey, map);
				connection.expire(btyKey, CacheKey.DEFAULT_CACHE_TIME_OUT);
				return null;

			}
		});

	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getObject(final String key)
	{
//		logger.info("getObject key:" + key);
		return redisTemplate.execute(new RedisCallback<Object>()
		{
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException
			{
				byte[] btyKey = redisTemplate.getStringSerializer().serialize(ZH_TAG + key);
				if (connection.exists(btyKey))
				{
					List<byte[]> value = connection.hMGet(btyKey, redisTemplate.getStringSerializer().serialize("obj"));
					return redisTemplate.getDefaultSerializer().deserialize(value.get(0));
				}
				return null;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObject(final String key, Class<T> clazz)
	{
		Object obj = getObject(key);
		if(null == obj) {
			return null;
		}
		return (T)obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteObject(final String key)
	{
//		logger.info("deleteObject key:" + key);
		redisTemplate.execute(new RedisCallback<Object>()
		{
			public Object doInRedis(RedisConnection connection)
			{
				connection.del(redisTemplate.getStringSerializer().serialize(ZH_TAG + key));
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObject(final String key, final Object body, final long seconds)
	{
//		logger.info("putObject data:" + body.toString() + " data expire:" + seconds);
		redisTemplate.execute(new RedisCallback<Object>()
		{
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException
			{
				byte[] btyKey = redisTemplate.getStringSerializer().serialize(ZH_TAG + key);
				Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
				map.put(redisTemplate.getStringSerializer().serialize("obj"), redisTemplate.getDefaultSerializer().serialize(body));
				connection.hMSet(btyKey, map);
				if (seconds > 0)
					connection.expire(btyKey, seconds);
				else
					connection.expire(btyKey, CacheKey.DEFAULT_CACHE_TIME_OUT);
				return null;

			}
		});
	}
}
