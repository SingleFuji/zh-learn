package com.zh.demo.service;

import com.zh.demo.model.User;

public interface UserService {

	public int insertUser(User user);
	
	public User selectByID(int id);
}
