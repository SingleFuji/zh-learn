package com.zh.utils.classpath;

public class ClasspathUtil {

	public static void getClasspath()
	{
		String classPath = Thread.currentThread().getContextClassLoader().getResource("//").getPath();
		System.out.println(classPath);
	}
	
	public static void main(String[] args) {
		getClasspath();
	}
}
