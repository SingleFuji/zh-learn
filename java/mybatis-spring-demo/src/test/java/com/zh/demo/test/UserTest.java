package com.zh.demo.test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zh.demo.model.User;
import com.zh.demo.service.UserService;

public class UserTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	private UserService userService;
    
    @Before
    public void before(){                                                                   
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                ,"classpath:conf/spring-mybatis.xml"});
        userService = (UserService) context.getBean("userService");
    }
     
    @Test
    public void addUser(){
        User user = new User();
        user.setId(1);
        user.setName("你好");
//        user.setState(2);
        System.out.println(userService.insertUser(user));
    }
    
    @Test
    public void selectByID()
    {
    	User user = userService.selectByID(1);
    	System.out.println(user.getName());
    }

}
