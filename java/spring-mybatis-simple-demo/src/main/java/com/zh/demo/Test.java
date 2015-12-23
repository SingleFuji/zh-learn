package com.zh.demo;

import java.io.IOException;
import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class Test {

		public static void main(String[] args) throws IOException {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
			
//			DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
//			TransactionFactory transactionFactory = new JdbcTransactionFactory();
//			Environment environment = new Environment("development", transactionFactory, dataSource);
//			Configuration configuration = new Configuration(environment);
//			configuration.addMapper(BlogMapper.class);
//			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		}
}
