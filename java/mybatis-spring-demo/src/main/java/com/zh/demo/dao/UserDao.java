package com.zh.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.zh.demo.model.User;

@Repository(value="userDao")
public interface UserDao {
	 /**
     * 添加新用户
     * 
     * @param user
     * @return
     */
    public int insertUser(User user);
    
    /**
     * 查询用户
     * 
     * @param id
     * @return
     */
    public User selectByID(@Param("id")int id);
}
