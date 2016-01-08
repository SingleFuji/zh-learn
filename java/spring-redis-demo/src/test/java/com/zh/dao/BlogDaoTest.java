package com.zh.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zh.po.Blog;

public class BlogDaoTest {

	private static final String JDBC_CONFIG = "classpath*:applicationContext.xml";
	
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { JDBC_CONFIG});
	private BlogDao blogDao = (BlogDao)context.getBean("blogDao");
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println(Thread.currentThread().getName()+"+++++++++BEGINE TEST++++++++++");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println(Thread.currentThread().getName()+"+++++++++END TEST++++++++++");
	}

	@Test
	public void test() {
		Blog blog = new Blog();
		blog.setId(1);
		blog.setContent("good");
		blog.setOwner("jack");
		blog.setTitle("Lesson 1");
		blogDao.insertOneBlog(blog);
	}

	@Test
	public void testSelect()
	{
		Blog blog = blogDao.selectByID(1);
		System.out.println(blog.getContent());
	}
}
