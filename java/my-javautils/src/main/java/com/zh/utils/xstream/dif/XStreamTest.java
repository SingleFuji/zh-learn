package com.zh.utils.xstream.dif;

import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamTest {

	private static final String xmlString = "<books><book price=\"108\"><name>Java编程思想</name><author>Bruce Eckel</author></book><book price=\"52\"><name>Effective Java</name><author>Joshua Bloch</author></book><book price=\"118\"><name>Java 7入门经典</name><author>Ivor Horton</author></book></books>";

	public static String toXml(Object obj) {
		XStream xstream = new XStream(new DomDriver("utf8"));
		xstream.processAnnotations(obj.getClass()); // 识别obj类中的注解
		/*
		 * // 以压缩的方式输出XML StringWriter sw = new StringWriter();
		 * xstream.marshal(obj, new CompactWriter(sw)); return sw.toString();
		 */
		// 以格式化的方式输出XML
		return xstream.toXML(obj);
	}

	public static <T> T toBean(String xmlStr, Class<T> cls) {
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(cls);
		@SuppressWarnings("unchecked")
		T t = (T) xstream.fromXML(xmlStr);
		return t;
	}

	public static Object simplexml2object(String xml, Object obj) {
		XStream xStream = new XStream(new DomDriver());
		xStream.alias(obj.getClass().getSimpleName(), obj.getClass());
		Object reobj = xStream.fromXML(xml);
		return reobj;
	}

	public static void test() {
		Books books = toBean(xmlString, Books.class);
		List<Book> list = books.getList();
		for (Book book : list) {
			System.out.println("name=" + book.getName() + "\tauthor="
					+ book.getAuthor() + "\tprice=" + book.getPrice());
		}
		System.out.println(toXml(books));
	}

	public static void main(String[] args) {

		// test();
		testWepayXML();
	}

	public static void testWepayXML() {
//		String wepayStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><return_code><![CDATA[SUCCESS]]></return_code>"
//				+ "<return_msg><![CDATA[]]></return_msg>"
////				+ "<result_code><![CDATA[FAIL]]></result_code>"
////				+ "<result_msg><![CDATA[经营类目填写有误，请检查后重新提交]]></result_msg>"
//				+ "</root>";
		
		String wepayStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[签名校验失败，请检查后重试]]></return_msg>"
						+ "</root>";
		String xmlStr = dealStrFromWepay(wepayStr, TransSettleXmlAddResp.class.getSimpleName());
		TransSettleXmlAddResp resp = (TransSettleXmlAddResp) toBean(xmlStr, TransSettleXmlAddResp.class);
		System.out.println(resp.getResult_code());
	}

	/**
	 * 删除微信返回的多余的字符 转换为符合规范的xml字符串
	 * 
	 * @param wepayStr
	 * @return
	 */
	public static String dealStrFromWepay(String wepayStr, String classStr) {
		String str = wepayStr;
		StringBuilder sb = new StringBuilder(str);
		int q = str.indexOf("?>");
		String orgiXmlStr = sb.substring(q + 2, sb.length());
		System.out.println(orgiXmlStr);
		String xmlStr = orgiXmlStr.replace("root", classStr);
		System.out.println(xmlStr);
		return xmlStr;
	}

}
