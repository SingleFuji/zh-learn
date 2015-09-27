package com.zh.demo.dao;

import org.springframework.stereotype.Service;

import com.zh.demo.model.User;

@Service("userDao")
public interface UserDao {
	 /**
     * 添加新用户
     * @param user
     * @return
     */
    public int insertUser(User user);
}
