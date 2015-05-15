package com.zh.logback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerThread implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(LoggerThread.class);
	
	private int count = 0;
	
	public void run() {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i = 0;i < 100;i++)
		{
			logger.info("=="+count++ +"==" + Thread.currentThread().getName()+":"+ f.format(System.currentTimeMillis()));
		}
	}
	
	public static void main(String[] args)
	{
		LoggerThread lt = new LoggerThread();
		for(int i = 0;i <30;i++)
		{
			Thread t = new Thread(lt);
			t.start();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
}
