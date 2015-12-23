package com.zh.xgd.txt;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MenuUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println(Thread.currentThread().getName()+":START.......");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println(Thread.currentThread().getName()+":FINISH.......");
	}

	@Test
	public void testReplaceSysguidByMenuLevel() {
		String srcStr="values ('249E822020DC5B36E053010414AC4558', '249E822020DA5B36E053010414AC4558', '微信', '1', '3000', '700', '', '', 0, 0, 0, 0, 0, '0', 0, 0, '0', '', '0', 0, 700, 0, 1111113, '0');";
		String modelID="7777777777777";
		String menuLevel = "6000";
		MenuUtils.replaceSysguidByMenuLevel(srcStr, modelID, menuLevel);
	}

}
