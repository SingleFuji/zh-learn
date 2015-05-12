package com.zh.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author zhouhang
 *�ڹ���srcĿ¼�½���logback.xml
ע��
1.logback���Ȼ����Ų���logback.groovy�ļ�;
2.��û���ҵ�ʱ���������Ų���logback-test.xml�ļ�;
3.��û���ҵ�ʱ���������Ų���logback.xml�ļ�;
4.�����Ȼû���ҵ�����ʹ��Ĭ�����ã���ӡ������̨��
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
		
		// logback�ṩ�Ŀ���ʹ�ñ����Ĵ�ӡ��ʽ�����Ϊ"Hello,Aub!"
		log.info("Hello,{}!", name);
		
		// �����ж������,���Ϊ��Hello,Aub! 3Q!��
		log.info("Hello,{}!   {}!", name, message);
		
		// ���Դ���һ�����飬���Ϊ"Fruit:  apple,banana"
		log.info("Fruit:  {},{}", fruits); 
	}
}