package com.zh.xgd.utils;

import java.io.UnsupportedEncodingException;

/*获取磁道信息中的附加数据
 * 返回参数字符串类型
 * 传入参数字符串类型
 * pan 主账号
 * validDate 有效期
 * serviceCode 服务代码
 * */
public class CalacuateCVN {
	public static String getcalacuate(String pan,String validDate,String serviceCode){
		String dataSource=pan+validDate+serviceCode;
		String result=null;
		for (int i = dataSource.length();i<32;i++) {
			dataSource = dataSource + "0";
		}
		String block1 = dataSource.substring(0,16);
		String block2 = dataSource.substring(16);
		SimulateCommonEncrypt service = new SimulateCommonEncrypt("0123456789ABCDEFFEDCBA9876543210");
		try {
			String result1 = service.encryptDes(block1);
			byte[] temp1 = HexBinary.decode(result1);
			byte[] temp2 = HexBinary.decode(block2);
			for (int i=0; i<8; i++) {
				temp1[i] = (byte) (temp1[i] ^ temp2[i]);
			}
			String result2 = HexBinary.encode(temp1);
			String result3 = service.encrypt3DES(result2);
			String number = "";
			String alpha = "";
			for (int i = 0; i < 16;i++) {
				char a = result3.charAt(i);
				if (a>'0' && a<'9') {
					number = number + a;
				} else if (a >= 'A'  &&  a <= 'F') {
					alpha = alpha + a;
				} else if (a >= 'a'  &&  a <= 'f') {
					a = (char) ((a - 'a' + 10) << 4);
					alpha = alpha + a;
				}
			}
			String number2 = "";
			for (int i = 0; i < alpha.length(); i++) {
				char a = alpha.charAt(i);
				int ca1 = Integer.valueOf(String.valueOf(a), 16);
				ca1 = ca1 - 10;
				number2 = number2 + ca1;
			}
			String result4 = number + number2;
			result=result4.substring(0,3);
			
		} catch (UnsupportedEncodingException e) {
		}
		return result;
	}
}