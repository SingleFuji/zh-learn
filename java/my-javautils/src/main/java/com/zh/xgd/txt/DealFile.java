package com.zh.xgd.txt;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class DealFile {

	private static final String FILE_PATH = "D:\\test";
	private static final String FILE_NAME="test.sql";
	private static final String WHOLE_PATH=FILE_PATH+File.separator+FILE_NAME;
	
	private static final String DES_FILE_PATH = "D:\\test";
	private static final String  DES_FILE_NAME="src.sql";
	private static final String  DES_WHOLE_PATH=DES_FILE_PATH+File.separator+DES_FILE_NAME;
	private static final String DEFAULT_ENCODEING="UTF-8";
	/**
	 * 将读取的文件输出到控制台
	 * 
	 * @throws IOException
	 */
	public String readFile() throws IOException
	{
		File file = new File(getFileName(WHOLE_PATH));
		List<String> lines = FileUtils.readLines(file, "UTF-8");
//		String fileStr = FileUtils.readFileToString(file);
		StringBuilder dealStr = new StringBuilder();
		for(String line: lines)
		{
			dealStr.append(MenuUtils.appendDoubleQuotation(line)).append(System.lineSeparator());
		}
		System.out.println(dealStr.toString());
		return dealStr.toString();
	}
	
	private void addDoubleQuotation() throws IOException
	{

		File file = new File(getFileName(WHOLE_PATH));
		List<String> lines = FileUtils.readLines(file, "UTF-8");
//		String fileStr = FileUtils.readFileToString(file);
		StringBuilder dealStr = new StringBuilder();
		for(String line: lines)
		{
			dealStr.append(MenuUtils.appendDoubleQuotation(line)).append(System.lineSeparator());
		}
		System.out.println(dealStr.toString());
	}
	
	public void dealTAGFile() throws IOException
	{
		File file = new File(getFileName(WHOLE_PATH));
		List<String> lines = FileUtils.readLines(file, "UTF-8");
		StringBuilder dealStr = new StringBuilder();
		for(String temp : lines)
		{
			dealStr.append(dealTagInfo(temp)).append(System.lineSeparator());
		}
		System.out.println(dealStr);
	}
	
	private String dealTagInfo(String info)
	{
		String[] tagArr = info.split("[(]");
		return tagArr[0]+"("+tagArr[1]+".getTag(), info.get);";
	}
	
	/**
	 *  从文件中读菜单结构构造信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public String readWechatMenuStructFile() throws IOException
	{
		File file = new File(getFileName(WHOLE_PATH));
		List<String> lines = FileUtils.readLines(file, "UTF-8");
		return MenuUtils.getModelSql(lines);
	}
	
	/**
	 * 从文件中读菜单详情信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public String readWechatMenuDetailFile() throws IOException
	{
		File file = new File(getFileName(WHOLE_PATH));
		List<String> lines = FileUtils.readLines(file, "UTF-8");
		return MenuUtils.getModelDetailSql(lines);
	}
	
	
	/**
	 * 将字符串写到文件中
	 * 
	 * @param writeInfo
	 * @throws IOException
	 */
	public void writeFile(String writeInfo) throws IOException
	{
		File file = new File(getFileName(DES_WHOLE_PATH));
		FileUtils.writeStringToFile(file, writeInfo, DEFAULT_ENCODEING);
	}
	
	public void writeFileAppend(String writeInfo) throws IOException
	{
		File file = new File(getFileName(DES_WHOLE_PATH));
		FileUtils.writeStringToFile(file, writeInfo, DEFAULT_ENCODEING, true);
	}
	
	/**
	 * 将源文本拷贝多次到目标文件
	 * 
	 * @param times
	 * @throws IOException
	 */
	public void copyFileByTimes(int times) throws IOException
	{
		File srcFile = new File(getFileName(WHOLE_PATH));
		String srcStr = FileUtils.readFileToString(srcFile);
		StringBuilder outStr = new StringBuilder();
		for(int i = 0;i<times;i++)
		{
			outStr.append(srcStr).append(System.lineSeparator());
		}
		writeFile(outStr.toString());
	}
	
	/**
	 * 针对不同系统格式化文件名
	 * 
	 * @param filename
	 * @return
	 */
	public String getFileName(String filename)
	{
		String normalized = FilenameUtils.normalize(filename);
		return normalized;
	}
	
	public void dealModelIDsArr() throws IOException
	{
		File file = new File(getFileName(WHOLE_PATH));
		List<String> lines = FileUtils.readLines(file, "UTF-8");
		StringBuilder modelIDArr = new StringBuilder();
		for(String modelID:lines)
		{
			modelIDArr.append(MenuUtils.appendDoubleQuotation(modelID)).append(System.lineSeparator());
		}
//		writeFile(modelIDArr.toString());
		System.out.println(modelIDArr.toString());
	}
	
	public void testFileSystemUtil() throws IOException
	{
		long freeSpace = FileSystemUtils.freeSpace(FILE_PATH);
	}
	
	/**
	 * 将菜单结构信息写入指定文件中
	 * 
	 * @throws IOException
	 */
	public void writeWechatMenuStruct() throws IOException
	{
		String writeInfo = readWechatMenuStructFile();
		writeFile(writeInfo);
	}
	
	/**
	 * 将菜单详情写入指定文件中
	 * 
	 * @throws IOException
	 */
	public void writeWechatMenuDetail() throws IOException
	{
		String writeInfo = readWechatMenuDetailFile();
		writeFile(writeInfo);
	}
	
}
