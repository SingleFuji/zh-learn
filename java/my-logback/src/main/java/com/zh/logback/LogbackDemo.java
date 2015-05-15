package com.zh.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author zhouhang
 *在工程src目录下建立logback.xml
注：
1.logback首先会试着查找logback.groovy文件;
2.当没有找到时，继续试着查找logback-test.xml文件;
3.当没有找到时，继续试着查找logback.xml文件;
4.如果仍然没有找到，则使用默认配置（打印到控制台）
 */
public class LogbackDemo {
	private static Logger log = LoggerFactory.getLogger(LogbackDemo.class);
	public static void main(String[] args) {
		log.trace("======trace");
		log.debug("======debug");
		log.info("======info");
		log.warn("======warn");
		log.error("======error");
		 
        String name = "Aub";
		String message = "3Q";
		String[] fruits = { "apple", "banana" };
		
		// logback提供的可以使用变量的打印方式，结果为"Hello,Aub!"
		log.info("Hello,{}!", name);
		
		// 可以有多个参数,结果为“Hello,Aub! 3Q!”
		log.info("Hello,{}!   {}!", name, message);
		
		// 可以传入一个数组，结果为"Fruit:  apple,banana"
		log.info("Fruit:  {},{}", fruits); 
		
		
		StringBuffer sql = new StringBuffer();
	    sql.append("select AUTOID,PAN,AMOUNT,TRADETIME, VOUCHERNO,TRADETYPE,STATUS,POSSEQNO,CLEARTIME,").append("REFNO,TERMNO,MERCHANTNO,BATCHNO,AUTHCODE,RTNCODE,TRADETYPECODE,REQMSGID,").append("RESMSGID,RESTRADETIME,RESCLEARTIME,RESREFNO,PROCESSCODE,CONCODE,CURRENCYCODE,ACCEPTORIDCODE,FWDINSTIDCODE,TRADETRANSTIME,ORGITRADETRANSTIME,ORGISEQNO,ORGIBATCHNO,ORGIREFNO,RESAMOUNT,").append("CREATOR,CREATETIME,OPERATOR,OPRTIME ").append(" from t_slsy_tradevoucher t where t.merchantno = ? and t.termno = ? ").append("and t.posseqno = ? and t.BATCHNO = ? and trunc(t.tradetime, 'dd') = trunc(sysdate, 'dd')");
	    System.out.println(sql.toString());
	}
}
