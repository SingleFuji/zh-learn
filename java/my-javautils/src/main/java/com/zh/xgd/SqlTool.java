package com.zh.xgd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class SqlTool {
	//要修改的字段的位置
	private static final int CHANGE_POSITION = 2;//7  1  2
	//循环的行数
	private static final int SQL_LINE_NO = 3;//3  5
	private static final String CODE_STYLE = "UTF-8";
	private static final String CHNAGE_WORDS = "sys_guid()";//"values (sys_guid()"
	private static final String BASE_PATH = "D:\\test\\";
	private static final String SOURCE_FILE = BASE_PATH + "src.sql";
	private static final String DISTINATION_FILE = BASE_PATH + "test.sql";
	private static final String MODEL = "rw";
	
	//1.从文件中读出一行
	//2.修改当前行内容
	//3.写回文件
	public static void readFile() throws IOException
	{
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(SOURCE_FILE);
		    sc = new Scanner(inputStream, CODE_STYLE);
		    int count = 0;
		    StringBuilder content = new StringBuilder();
		    while (sc.hasNextLine()) {
		    	count++;
		        String line = sc.nextLine();
		        if(count == (SQL_LINE_NO-1))
		        {
		        	line = dealString(line, CHANGE_POSITION);
		        }
		        else
		        {
		        	line += "\r\n";
		        }
		        content.append(line);
		        if(count == SQL_LINE_NO)
		        {
		        	count = 0;
		        }
		        System.out.println(line);
		    }
		    
		    writeFile(content.toString());
		    
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} finally {
		    if (inputStream != null) {
		        inputStream.close();
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
	}
	
	
	public static void writeFile(String str)
	{
		File file = new File(DISTINATION_FILE);
		try {
			@SuppressWarnings("resource")
			OutputStream out = new FileOutputStream(file);
			out.write(str.getBytes(CODE_STYLE));
//			System.out.print("out is print!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	  public static void appendMethod(String content) {
	        try {
	            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
	            FileWriter writer = new FileWriter(DISTINATION_FILE, true);
	            writer.write(content);
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	/**
	 * 
	 * @param info 待处理的字符串
	 * @param flag 要替换的字符串的位置
	 * @return
	 */
	private static String dealString(String info, int flag)
	{
		flag -= 1;
		StringBuilder result = new StringBuilder();
		String[] strArr = info.split(",");
		int size = strArr.length;
		for(int i = 0;i < size;i++)
		{
			String temp = strArr[i];
			if(i == flag)
			{
				temp = CHNAGE_WORDS;
			}
			if(i != (size-1))
			{
				temp += ",";
			}
			result.append(temp);
		}
		result.append("\r\n");
		return result.toString();
	}
	
	private void appendInfo(String content) 
	{
		try {
			RandomAccessFile raf = new RandomAccessFile(DISTINATION_FILE, MODEL);
				raf.seek(raf.length());
				raf.write(content.getBytes(CODE_STYLE));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		try {
			readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
