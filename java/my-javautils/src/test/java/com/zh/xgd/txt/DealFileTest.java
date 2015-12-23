package com.zh.xgd.txt;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DealFileTest {

	private DealFile excutor = new DealFile();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("====================START=================");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("====================END=================");
	}

	@Test
	public void testReadFile() throws IOException {
//		DealFile excutor = new DealFile();
//		excutor.readFile();
		excutor.dealTAGFile();
	}
	
	@Test
	public void testWriteFile() throws IOException
	{
		String writeInfo = excutor.readFile();
		excutor.writeFile(writeInfo);
	}

	@Test
	public void testWriteFileAppend() throws IOException
	{
		String writeInfo = excutor.readFile();
		excutor.writeFileAppend(writeInfo);
	}
	
	@Test
	public void testCopyFileByTimes() throws IOException
	{
		excutor.copyFileByTimes(16);
	}
	
	@Test
	public void testDealModelIDsArr() throws IOException
	{
		excutor.dealModelIDsArr();
	}
	
	@Test
	public void testWriteWechatMenuStruct() throws IOException
	{
		excutor.writeWechatMenuStruct();
	}
	
	@Test
	public void testwriteWechatMenuDetail() throws IOException
	{
		excutor.writeWechatMenuDetail();
	}
}
