package com.zh.xgd;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Test {
	public static void main(String[] args) {
		// String isoldTradeFromNewCharge = null;
		// boolean isoldTradeFromNewChargeBoolean =
		// Boolean.valueOf(isoldTradeFromNewCharge);

		// String test =
		// "<mch__id>1242722402</mch__id>\n<merchant__name>嘉联（测试）</merchant__name>\n<merchant__shortname>嘉联（测试）</merchant__shortname>";
		// System.out.println(test);
		// System.out.println(test.replace("__", "_"));

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("appid", "wxd930ea5d5a258f4f");
		hashMap.put("business", "受理服务");
		hashMap.put("service_phone", "40000008");
		hashMap.put("merchant_remark", "测试");
		hashMap.put("merchant_name", "嘉联");
		hashMap.put("sign", "95212B5ED708736DDF479832B105DEC2");
		
		mapToXml(hashMap);
		
		Map<String, String> sortedParams = new TreeMap<String, String>(hashMap);
		mapToXml(sortedParams);
		
		
	}
	//<string><![CDATA[appid]]></string>
	public static String formatStringXml(String key,String value)
	{
		return "<"+key+"><![CDATA["+value+"]]></"+key+">";
	}
	
	public static String mapToXml(Map<String, String> hashMap)
	{
		String head = "<xml>";
		String tail = "</xml>";
		StringBuffer sb = new StringBuffer();
		sb.append(head);
		Iterator iter = hashMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String)entry.getKey();
			String val = (String)entry.getValue();
			String str = formatStringXml(key, val);
			sb.append("\r\t").append(str);
		}
		sb.append(tail);
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	public static void  test()
	{
	
	
//	<xml>
//	<appid>wxd930ea5d5a258f4f</appid>
//	<business>受理服务</business>
//	<mch_id>1242722402</mch_id>
//	<merchant_name>嘉联</merchant_name>
//	<merchant_remark>测试</merchant_remark>
//	<merchant_shortname>嘉联test</merchant_shortname>
//	<service_phone>40000008</service_phone>
//	<sign>95212B5ED708736DDF479832B105DEC2</sign>
//</xml>
//	
//	
//	<xml>
//	<appid><![CDATA[wxd930ea5d5a258f4f]]></appid>
//	<business><![CDATA[受理服务]]></business>
//	<mch_id><![CDATA[1242722402]]></mch_id>
//	<merchant_name><![CDATA[嘉联]]></merchant_name>
//	<merchant_remark><![CDATA[测试]]></merchant_remark>
//	<merchant_shortname><![CDATA[嘉联test]]></merchant_shortname>
//	<service_phone><![CDATA[40000008]]></service_phone>
//	<sign><![CDATA[95212B5ED708736DDF479832B105DEC2]]></sign>
//	</xml>

	}
	
}
