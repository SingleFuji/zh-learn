package com.zh.cache.redis;

/**
 * 缓存键值
 * @author wujun
 *
 */
public class CacheKey {
	
	/**默认缓存的过期时间：一天*/
	public final static long DEFAULT_CACHE_TIME_OUT = 86400/*一天*/;
	
	/**交易缓存的过期时间*/
	public final static long TRADE_CACHE_TIME_OUT = 300;
	
	/** 缓存时间2小时 **/
	public final static long TWO_HOUR = 7200;
	
	/**各个环境缓存数据的key前缀*/
	public final static String TRADE_CACHE_KEY_PREFIX_KEY = "trade_cache_key_prefix_key";
	
	/**缓存卡号的key*/
	public static final String BUILD_CARD_NO_CACHE_KEY = "trade_build_card_no_key";
	
	/**缓存商户关系的key*/
	public static final String BUILD_RELATION_DATA_CACHE_KEY = "trade_build_relation_data_key";
	
	/**缓存渠道的key*/
	public static final String BUILD_CHANNEL_DATA_CACHE_KEY = "trade_build_channel_data_key";
	
	/**动态积分缓存key*/
	public static final String INTEGRAL_PRE_KEY = "INTEGRAL_PRE_KEY";
	
	//订单号key
	public static final String ORDER_NO_KEY = "ORDER_NO_KEY";
	
	private CacheKey () {}
}
