package com.zh.utils.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;



public class ReflectUtil {

	public static void formatResultParam(Object param,
			Map<String, String> busiMap) throws IntrospectionException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Object obj = null;
		Class clazz = param.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.getName().equals("serialVersionUID")) {
				continue;
			}
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(),
					clazz);
			Method getMethod = pd.getReadMethod();// 获得get方法
			obj = getMethod.invoke(param);// 执行get方法返回一个Object
			if (null != obj) {
				busiMap.put(field.getName(), obj.toString());
			}

		}
	}
}