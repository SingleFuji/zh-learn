package com.zh.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.zh.po.Blog;

@Service("blogDao")
public interface BlogDao {

	public Blog selectByID(@Param("blogID")int id);
	
	public int insertOneBlog(Blog blog);
}
