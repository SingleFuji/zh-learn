package com.zh.xgd.utils;

public class LRC {
	public static int getLRC(String trackInfo){
		byte lrc=0;
		byte[] ch=trackInfo.getBytes();
		int lrc2=0;
		for(int k=0;k<ch.length;k++){
			ch[k]=(byte) (ch[k] & 0x0f);
//			System.out.println(ch[k]);
			if(check(ch[k],4)==0)
			{
				ch[k] |= 0x10;
			}
			lrc ^= ch[k];
		}
		if((check(lrc,4))==0)
		{
			lrc |= 0x10;
		}
		lrc2=lrc;
		return lrc2;
	}
	public static int check(byte str,int len){
		byte tmpstr=str;
		int count=0;
		for(int i=0;i<len;i++)
		{
			if((tmpstr&0x01)!=0)
			{
				count++;
			}
			tmpstr = (byte) (tmpstr>>1);
		}

		if(count%2 == 1)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

}
