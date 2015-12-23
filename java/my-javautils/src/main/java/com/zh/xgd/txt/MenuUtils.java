package com.zh.xgd.txt;

import java.util.List;

public class MenuUtils {

	private static final String SYS_GUID = "sys_guid()";
	
	private static String[] modelIDArr = {
			"22112AD885C50442E053010414AC3BC0",
			"22112916C6EA03EFE053010414AC2172",
			"2211275F4D1C0383E053010414AC286E",
			"22112C18CD3F049AE053010414AC13D2",
			"24DB6B0423431F89E053010414AC802C",
			"234D3B870FB95DC2E053010414AC7F5A",
			"23501041C45F7D62E053010414AC867A",
			"234FFF586CAF7C63E053010414ACA0F8",
			"23508163B5800467E053010414AC3CF0",
			"243E87222165416DE053010414AC397A",
			"242C2C3392E67A0BE053010414AC6BA5",
			"1E5BA6F6900E44F1E053010414AC6448",
//			"24DCA132A80646FDE053010414ACF5A8",
			"5E958E964D5CC510F724929676702B35",
			"1DA4CB7E2D2270D6E053010414AC185C",
			"8FCABFB678B1C8719F78C4E3CCA2144E",
			"1FAC0784090C2B1BE053010414ACAB86",
//			"1A1C45C74DF46AF8E053010414AC95CA"
	};
	
	private static String[] model3000Arr = {
//			"1A1C45C74DF46AF8E053010414AC95CA",//主扫
			"1E5BA6F6900E44F1E053010414AC6448",
			"1FAC0784090C2B1BE053010414ACAB86"
	};
	
	private static String[] model4000Arr = {
			"234D3B870FB95DC2E053010414AC7F5A",
			"234FFF586CAF7C63E053010414ACA0F8",
			"23501041C45F7D62E053010414AC867A",
			"23508163B5800467E053010414AC3CF0",
			"243E87222165416DE053010414AC397A"
	};

	private static String[] model5000Arr = {
			"1DA4CB7E2D2270D6E053010414AC185C",
			"22112916C6EA03EFE053010414AC2172",
			"22112C18CD3F049AE053010414AC13D2",
			"24DB6B0423431F89E053010414AC802C",
//			"2674E24612D807E0E053010414AC122D",//未知
			"5E958E964D5CC510F724929676702B35"
	};

	private static String[] model6000Arr = {
			"242C2C3392E67A0BE053010414AC6BA5",
			"8FCABFB678B1C8719F78C4E3CCA2144E"
	};
	
	private static String[] model8000Arr = {
			"2211275F4D1C0383E053010414AC286E",
			"22112AD885C50442E053010414AC3BC0"
	};
	
	private static String[] detailSIDArr = {
			"26EB996DF4D77D3DE053010414ACE421",
			"26EB996DF4D87D3DE053010414ACE421",
			"26EB996DF4D97D3DE053010414ACE421",
			"26EB996DF4BB7D3DE053010414ACE421",
			"26EB996DF4BC7D3DE053010414ACE421",
			"26EB996DF4BD7D3DE053010414ACE421",
			"26EB996DF4BF7D3DE053010414ACE421",
			"26EB996DF4C07D3DE053010414ACE421",
			"26EB996DF4C17D3DE053010414ACE421",
			"26EB996DF4F37D3DE053010414ACE421",
			"26EB996DF4F47D3DE053010414ACE421",
			"26EB996DF4F57D3DE053010414ACE421",
			"26EB996DF4DB7D3DE053010414ACE421",
			"26EB996DF4DC7D3DE053010414ACE421",
			"26EB996DF4DD7D3DE053010414ACE421",
			"26EB996DF4F77D3DE053010414ACE421",
			"26EB996DF4F87D3DE053010414ACE421",
			"26EB996DF4F97D3DE053010414ACE421",
			"26EB996DF4DF7D3DE053010414ACE421",
			"26EB996DF4E07D3DE053010414ACE421",
			"26EB996DF4E17D3DE053010414ACE421",
			"26EB996DF4C37D3DE053010414ACE421",
			"26EB996DF4C47D3DE053010414ACE421",
			"26EB996DF4C57D3DE053010414ACE421",
			"26EB996DF4C77D3DE053010414ACE421",
			"26EB996DF4C87D3DE053010414ACE421",
			"26EB996DF4C97D3DE053010414ACE421",
			"26EB996DF4CB7D3DE053010414ACE421",
			"26EB996DF4CC7D3DE053010414ACE421",
			"26EB996DF4CD7D3DE053010414ACE421",
			"26EB996DF4CF7D3DE053010414ACE421",
			"26EB996DF4D07D3DE053010414ACE421",
			"26EB996DF4D17D3DE053010414ACE421",
			"26EB996DF4EB7D3DE053010414ACE421",
			"26EB996DF4EC7D3DE053010414ACE421",
			"26EB996DF4ED7D3DE053010414ACE421",
			"26EB996DF4D37D3DE053010414ACE421",
			"26EB996DF4D47D3DE053010414ACE421",
			"26EB996DF4D57D3DE053010414ACE421",
			"26EB996DF4E37D3DE053010414ACE421",
			"26EB996DF4E47D3DE053010414ACE421",
			"26EB996DF4E57D3DE053010414ACE421",
			"26EB996DF4E77D3DE053010414ACE421",
			"26EB996DF4E87D3DE053010414ACE421",
			"26EB996DF4E97D3DE053010414ACE421",
			"26EB996DF4EF7D3DE053010414ACE421",
			"26EB996DF4F07D3DE053010414ACE421",
			"26EB996DF4F17D3DE053010414ACE421"
	};
	
	/**
	 * 拼接分号
	 * 22112AD885C50442E053010414AC3BC0-->'22112AD885C50442E053010414AC3BC0',
	 * @param SID
	 * @return
	 */
	public static String appendQuotation(String SID)
	{
		return "'"+SID+"',";
	}
	
	public static String appendQuotation(String SID, boolean isComma)
	{
		if(isComma)
		{
			return appendQuotation(SID);
		}
		return "'"+SID+"'";
	}
	
	public static String appendDoubleQuotation(String SID)
	{
		return '"'+SID+'"'+",";
	}
	
	/**
	 * 将菜单结构表的sid换成sys_guid 并更新 model_id
	 * 
	 * @param srcStr
	 * @return
	 */
	public static String replaceSysguid(String srcStr, String modelID)
	{
		StringBuilder tempBuf = new StringBuilder();
		String[] strArr = srcStr.split(",");
		int firstCommaIndex = srcStr.indexOf(",");
		int secondCommaIndex = srcStr.indexOf(",", firstCommaIndex+1);
		int strLen = srcStr.length();
		String tail = srcStr.substring(secondCommaIndex+1, strLen);
		String tempHead = strArr[0];
		String head = tempHead.substring(0,tempHead.indexOf("(")+1)+"sys_guid(),";
		String middle = appendQuotation(modelID);
		tempBuf.append(head).append(middle).append(tail);
		return tempBuf.toString();
	}
	
	/**
	 * 将menuLevel以及modelID变更到指定SQL中
	 * 
	 * @param srcStr
	 * @param modelID
	 * @param menuLevel
	 * @return
	 */
	public static String replaceSysguidByMenuLevel(String srcStr, String modelID, String menuLevel)
	{
		String[] strArr = srcStr.split(",");
		String[] copyedStrArr = strArr.clone();
		String tempHead = strArr[0];
		String head = tempHead.substring(0,tempHead.indexOf("(")+1)+SYS_GUID;
		copyedStrArr[0]=head;
		copyedStrArr[1]=appendQuotation(modelID, false);
		copyedStrArr[4]=appendQuotation(menuLevel, false);
		return appendMenuStr(copyedStrArr);
	}
	
	/**
	 * 处理菜单详情SQL
	 * 
	 * @param srcStr
	 * @param modelID
	 * @param menuLevel
	 * @return
	 */
	public static String replaceMenuDetailSysguid(String srcStr, String modelID)
	{
		String[] strArr = srcStr.split(",");
		String[] copyedStrArr = strArr.clone();
		String tempHead = strArr[0];
		copyedStrArr[0] = tempHead.substring(0,tempHead.indexOf("(")+1)+appendQuotation(modelID, false);
		copyedStrArr[1]=SYS_GUID;
		return appendMenuStr(copyedStrArr);
	}
	
	/**
	 * 拼装字符串
	 * 
	 * @param copyedStrArr
	 * @return
	 */
	private static String appendMenuStr(String[] copyedStrArr)
	{
		StringBuilder tempBuf = new StringBuilder();
		int copyedStrArrLen = copyedStrArr.length;
		for(int i=0;i<copyedStrArrLen;i++)
		{
			String temp = copyedStrArr[i];
			if(i==copyedStrArrLen-1)
			{
				tempBuf.append(temp);
			}else
			{
				tempBuf.append(temp).append(",");
			}
		}
		return tempBuf.toString();
	}
	
	/**
	 * 获取指定次序的符号索引
	 * 
	 * @param str
	 * @param seq
	 * @return
	 */
	public static int getStringIndex(String str, int seq)
	{
		int index  = 0;
		for(int i=index;i<seq;i++)
		{
			index = str.indexOf(",",index+1);
		}
		return index;
	}
	
//	public static void main(String[] args) {
//		String str="values ('249E822020DC5B36E053010414AC4558', '249E822020DA5B36E053010414AC4558', '微信', '1', '3000', '700', '', '', 0, 0, 0, 0, 0, '0', 0, 0, '0', '', '0', 0, 700, 0, 1111113, '0');";
//		int seq = 3;
//		getStringIndex(str, seq);
//	}
	
	/**
	 * 
	 * @param lines
	 * @return
	 */
	public static String getModelSql(List<String> lines)
	{
		StringBuilder sqlAll = new StringBuilder();
		int count=1;
		int modelIDCount = 0;
		for(String temp : lines)
		{
			String toAppend=temp;
			if(count % 3 == 2)
			{//第一个',' 处理那句话之后拼装
//				toAppend = MenuUtils.replaceSysguid(temp, modelIDArr[modelIDCount]);
				toAppend = MenuUtils.replaceSysguidByMenuLevel(temp, getModelInArr16(modelIDCount), getMenuLevel16(count, modelIDCount));
			}
			sqlAll.append(toAppend+System.lineSeparator());
			if(count  % 12 == 0)
			{//更新ModelID
				modelIDCount++;
			}
			count++;
		}
		System.out.println(sqlAll.toString());
		return sqlAll.toString();
	}
	
	public static String getModelDetailSql(List<String> lines)
	{
		StringBuilder sqlAll = new StringBuilder();
		int count=1;
		for(String temp : lines)
		{
			String toAppend=temp;
			if(count % 3 == 2)
			{
				toAppend = MenuUtils.replaceMenuDetailSysguid(temp, getDetailSIDArr123(count));
			}
			sqlAll.append(toAppend+System.lineSeparator());
			count++;
		}
		System.out.println(sqlAll.toString());
		return sqlAll.toString();
	}
	
	/**
	 * 
	 * @param lines
	 * @return
	 */
	public static String getAllFileStr(List<String> lines)
	{
		StringBuilder str = new StringBuilder();
		for(String temp : lines)
		{
			str.append(temp).append(System.lineSeparator());
		}
		return str.toString();
	}
	
	private static String getModelInArr18(int modelIDCount)
	{
		if(modelIDCount <3 )
		{//3000
			return model3000Arr[modelIDCount];
		}else if(modelIDCount <8)
		{//4000
			return model4000Arr[modelIDCount-3];
		}else if(modelIDCount <14)
		{//5000
			return model5000Arr[modelIDCount-8];
		}else if(modelIDCount <16)
		{//6000
			return model6000Arr[modelIDCount-14];
		}else if(modelIDCount <=18)
		{//8000
			return model8000Arr[modelIDCount-16];
		}
		return null;
	}
	
	private static String getModelInArr16(int modelIDCount)
	{
		if(modelIDCount <2)
		{//3000
			return model3000Arr[modelIDCount];
		}else if(modelIDCount <7)
		{//4000
			return model4000Arr[modelIDCount-2];
		}else if(modelIDCount <12)
		{//5000
			return model5000Arr[modelIDCount-7];
		}else if(modelIDCount <14)
		{//6000
			return model6000Arr[modelIDCount-12];
		}else if(modelIDCount <16)
		{//8000
			return model8000Arr[modelIDCount-14];
		}
		return null;
	}
	
	private static String getMenuLevel18(int count, int modelIDCount)
	{
		if(modelIDCount <3 )
		{//3000
			return dealMenuLevel(count, 3);
		}else if(modelIDCount <8)
		{//4000
			return dealMenuLevel(count, 4);
		}else if(modelIDCount <14)
		{//5000
			return dealMenuLevel(count, 5);
		}else if(modelIDCount <16)
		{//6000
			return dealMenuLevel(count, 6);
		}else if(modelIDCount <18)
		{//8000
			return dealMenuLevel(count, 8);
		}
		return null;
	}
	
	private static String getMenuLevel16(int count, int modelIDCount)
	{
		if(modelIDCount <2 )
		{//3000
			return dealMenuLevel(count, 3);
		}else if(modelIDCount <7)
		{//4000
			return dealMenuLevel(count, 4);
		}else if(modelIDCount <12)
		{//5000
			return dealMenuLevel(count, 5);
		}else if(modelIDCount <14)
		{//6000
			return dealMenuLevel(count, 6);
		}else if(modelIDCount <16)
		{//8000
			return dealMenuLevel(count, 8);
		}
		return null;
	}
	
	private static String dealMenuLevel(int count, int level)
	{
		int key = count%12;
		switch (key) {
		case 2:
			return level+"000";
		case 5:
			return level+"100";
		case 8:
			return level+"200";
		case 11:
			return level+"300";
		default:
			return null;
		}
	}
	
	/**
	 * 
	 * 
	 * @param count
	 * @return
	 */
	private static String getDetailSIDArrDefault(int count)
	{
		int tempKey = count%183;
		int arrKey = count/183;
		if(tempKey < 75)
		{
			arrKey  = arrKey*3+0;
		}else if(tempKey < 141)
		{
			arrKey = arrKey*3+1;
		}else if(tempKey < 183)
		{
			arrKey = arrKey*3+2;
		}
		return detailSIDArr[arrKey];
	}
	
	private static String getDetailSIDArr123(int count)
	{
		int tempKey = count%183;
		int arrKey = count/183;
		if(tempKey < 66)
		{
			arrKey  = arrKey*3+0;
		}else if(tempKey < 141)
		{
			arrKey = arrKey*3+1;
		}else if(tempKey < 183)
		{
			arrKey = arrKey*3+2;
		}
		return detailSIDArr[arrKey];
	}
	
}
