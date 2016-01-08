package com.zh.cache;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zh.cache.redis.RedisStringService;

public class BlogRedisTest {

	private static final String locatorFactorySelector = "classpath*:beanRefFactory.xml";
	private static final String REDIS_CONFIG = "classpath*:redisContext.xml";
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { REDIS_CONFIG});
	private RedisStringService redisStringService = (RedisStringService)context.getBean("redisStringService");
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("")+"+++++++++BEGINE TEST++++++++++");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println(ClassLoader.getSystemResource("")+"+++++++++END TEST++++++++++");
	}

	@Test
	public void testPutString() {
		String sessionId = "we";
		String body = "21guys";
		redisStringService.putString(sessionId, body);
	}
	
	@Test
	public void testGetString() {
		String sessionId = "we";
		String result = redisStringService.getString(sessionId);
		System.out.println(result);
	}

}
