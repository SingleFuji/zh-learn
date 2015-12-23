package com.zh.utils.luhn;

public class TestOdd {

	public static void getNum()
	{
		String cardNo = "6228480368801971574";
		String subCardNo = cardNo.substring(0, cardNo.length()-1);
		int half = cardNo.length()/2;
		int oddSum = 0;//奇数
		int evenSum = 0;//偶数
		
		for(int i = 0;i < half;i++)
		{
			int tempOdd = Integer.valueOf(subCardNo.charAt(2*i+1) - 48) *2;
			if(tempOdd > 10)
			{
				tempOdd = tempOdd/10 + tempOdd%10;
			}
			oddSum += tempOdd;
			int tempEven = (Integer.valueOf(subCardNo.charAt(2*i)) - 48);
//			if(tempEven > 10)
//			{
//				tempEven = tempEven/10 + tempEven%10;
//			}
			evenSum += tempEven;
		}
		System.out.println("奇数位和："+oddSum + "偶数位和："+ evenSum);
		int totalSum = evenSum + oddSum;
		System.out.println("总和："+ totalSum);
		
//		System.out.println(cardNo.charAt(0)+" "+cardNo.charAt(1));
	}
	
	public static void main(String[] args) {
		getNum();
	}
}
