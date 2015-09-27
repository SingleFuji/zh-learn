package com.zh.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zh.demo.dao.UserDao;
import com.zh.demo.model.User;
import com.zh.demo.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
 
    @Autowired
    private UserDao userDao;
     
    public int insertUser(User user) {
        // TODO Auto-generated method stub
        return userDao.insertUser(user);
    }
 
}
