package com.zh.cache.redis.impl;

import java.io.Serializable;
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
import com.zh.cache.redis.RedisStringService;

/**
 * redis处理类
 * 
 * @copyrights 新国都技术股份有限公司
 * @author lin_song
 * @Date 2014-3-12
 * @desc
 * @version
 * @tosee
 */
@Service("redisStringService")
public class RedisStringServiceImpl implements RedisStringService
{

	private static final String ZH_STR_TAG = "zh.test.";//com.xinguodu.prepaidcard.sessionid.
	
//	private static final Logger logger = LoggerFactory.getLogger(RedisStringServiceImpl.class);

	@Resource
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	@Override
	public void putString(final String sessionId, final String body)
	{
//		logger.info("putString data:" + body);
		redisTemplate.execute(new RedisCallback<Object>()
		{
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException
			{
				byte[] key = redisTemplate.getStringSerializer().serialize(ZH_STR_TAG + sessionId);
				Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
				map.put(redisTemplate.getStringSerializer().serialize("body"), redisTemplate.getStringSerializer().serialize(body));
				connection.hMSet(key, map);
				connection.expire(key, CacheKey.DEFAULT_CACHE_TIME_OUT);
				return null;
			}
		});
	}

	@Override
	public String getString(final String sessionId)
	{
//		logger.info("getString sessionid:" + sessionId);
		return redisTemplate.execute(new RedisCallback<String>()
		{
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException
			{
				byte[] key = redisTemplate.getStringSerializer().serialize(ZH_STR_TAG + sessionId);
				if (connection.exists(key))
				{
					List<byte[]> value = connection.hMGet(key, redisTemplate.getStringSerializer().serialize("body"));
					String body = redisTemplate.getStringSerializer().deserialize(value.get(0));
					return body;
				}
				return null;
			}
		});
	}

	@Override
	public void deleteString(final String sessionId)
	{
//		logger.info("deleteString sessionid:" + sessionId);
		redisTemplate.execute(new RedisCallback<Object>()
		{
			public Object doInRedis(RedisConnection connection)
			{
				connection.del(redisTemplate.getStringSerializer().serialize(ZH_STR_TAG + sessionId));
				return null;
			}
		});
	}

	@Override
	public void putString(final String sessionId, final String body, final long seconds) {
//		logger.info("putString data:" + body);
		redisTemplate.execute(new RedisCallback<Object>()
		{
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException
			{
				byte[] key = redisTemplate.getStringSerializer().serialize(ZH_STR_TAG + sessionId);
				Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
				map.put(redisTemplate.getStringSerializer().serialize("body"), redisTemplate.getStringSerializer().serialize(body));
				connection.hMSet(key, map);
				if (seconds > 0)
					connection.expire(key, seconds);
				else
					connection.expire(key, CacheKey.DEFAULT_CACHE_TIME_OUT);
				return null;
			}
		});
	}

	@Override
	public boolean setNX(final String sessionId, final String body, final long seconds) {
//		logger.info("setNX data:" + body);
		return redisTemplate.execute(new RedisCallback<Boolean>()
		{
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException
			{
				byte[] key = redisTemplate.getStringSerializer().serialize(ZH_STR_TAG + sessionId);
				
				byte[] value = redisTemplate.getStringSerializer().serialize(body);
				boolean bool = connection.setNX(key, value);
				if (bool)
				{
					//如果设置成功了，设置过期时间
					connection.expire(key, seconds > 0 ? seconds : CacheKey.DEFAULT_CACHE_TIME_OUT);
				}
				return bool;
			}
		});
	}
}
