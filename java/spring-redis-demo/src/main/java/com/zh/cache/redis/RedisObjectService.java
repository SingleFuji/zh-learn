package com.zh.cache.redis;

public interface RedisObjectService
{

    /**
     * 向redis里存入对象
     * @param sessionid
     * @param bf
     */
	void putObject(final String key, final Object body);
	
	/**
	 * 向redis里存入对象,并指定过期时间
	 * @param key
	 * @param body
	 * @param seconds
	 */
	void putObject(final String key, final Object body,final long seconds);

	/**
	 * 从redis里取出对象
	 * @param sessionid
	 * @return
	 */
	Object getObject(final String key);
	
	/**
	 * 从redis里取出对象
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> T getObject(final String key, Class<T> clazz);

	/**
	 * 删除redis里的对象
	 * @param sessionId
	 */
	void deleteObject(final String key);


}
