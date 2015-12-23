package com.zh.utils.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class CurCounter {

	private static AtomicInteger counter = new AtomicInteger(0);
	
	public static int add()
	{
		return counter.incrementAndGet();
	}

	public static void testCur()
	{
		long start = System.currentTimeMillis();
		for(int i=0;i<50;i++)
		{
			new Thread(new Runnable() {
				
				public void run() {
					// TODO Auto-generated method stub
					for(int j=0;j<50;j++)
					{
						System.out.println("I="+Thread.currentThread().getName()+"J="+j+":"+add());
					}
					
				}
			}).start();
		}
		long end = System.currentTimeMillis();
		System.out.println("================"+ (end-start)+"========================");
	}
	
	public static void main(String[] args) {
		testCur();
	}
}
