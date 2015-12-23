package com.zh.utils.random;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestRandom {

	public static void main(String[] args) {
		String rndStr = random(32);
		System.out.println(rndStr);
		for(int i=0;i<5000;i++)
		{
			rndStr = RandomString(32);
			System.out.println(rndStr);
		}
		
	}

	/** 产生一个随机的字符串*/  
	public static String RandomString(int length) {  
	    String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  
	    Random random = new Random();  
	    StringBuffer buf = new StringBuffer();  
	    for (int i = 0; i < length; i++) {  
	        int num = random.nextInt(62);  
	        buf.append(str.charAt(num));  
	    }  
	    return buf.toString();  
	}  
	
	public static String random(int length) {
		StringBuilder builder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			builder.append((char) (ThreadLocalRandom.current().nextInt(33, 128)));
		}
		return builder.toString();
	}

	// Java实战项目里面介绍了一些不正确使用java.util.Random API的危害。这个教训告诉我们不要使用：
	// Math.abs(rnd.nextInt())%n
	// 而使用：
	// rnd.nextInt(n)
	public static void diffRandom() {
		double oleRnd = Math.floor(Math.random() * 11);
		System.out.println(oleRnd);

		double num = java.lang.Math.random();
		System.out.println(num);

		int r = new java.util.Random().nextInt(10);
		System.out.println(r);

		// 这个新的API综合了其他两种方法的优点：单一实例/静态访问 ThreadLocalRandom也比其他任何处理高并发的方法要更快
		int curR = java.util.concurrent.ThreadLocalRandom.current().nextInt(10);
		System.out.println(curR);

		Random rnd = new Random();
		double gaus = rnd.nextGaussian();
		System.out.println(gaus);
		System.out.println("++++++++++++++++++++++++++++++");
		for (int i = 0; i < 5000; i++) {
			int t = java.util.concurrent.ThreadLocalRandom.current().nextInt(
					5000);
			System.out.println(t);
		}
	}
}
