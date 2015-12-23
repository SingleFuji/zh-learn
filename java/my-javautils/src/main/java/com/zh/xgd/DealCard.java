package com.zh.xgd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DealCard {
	
	private static final String BASE_PATH = "D:\\test\\";
	private static final String SOURCE_FILE = BASE_PATH + "src.sql";
	private static final String MODEL = "rw";
	private static final String CODE_STYLE = "UTF-8";
	
	public static void main(String[] args) {
		FileInputStream inputStream = null;
		Scanner sc = null;
		 try {
			inputStream = new FileInputStream(SOURCE_FILE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 sc = new Scanner(inputStream, CODE_STYLE);
		 while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		        StringBuffer sb = new StringBuffer();
//		        String head = line.substring(0,6);
//		        String tail = line.substring(line.length()-4,line.length());
//		        System.out.println(head+"*******"+tail);//上下网卡号
		        System.out.println("add_filter_proxy "+line+";");//分流商户号终端号
		        
		 }
	}
}
