package com.zh.utils.string;

public class StringUtils {

	public static void main(String[] args) {
		String num = formatVoucherNo(1205);
		int res = disFormatVoucherNo(num);
		System.out.println(num);
		System.out.println(res);
	}
	
	/**
	 * 将数字格式化成6位流水  空位补0
	 * @param voucherNo
	 * @return
	 */
	public static String formatVoucherNo(int voucherNo)
	{
		return String.format("%06d", voucherNo);
	}

	/**
	 * 六位订单号转换成数字
	 * @param voucher
	 * @return
	 */
	public static int disFormatVoucherNo(String voucher)
	{
		return Integer.valueOf(voucher);
	}
	
}
