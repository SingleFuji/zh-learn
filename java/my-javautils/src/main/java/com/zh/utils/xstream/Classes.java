package com.zh.utils.xstream;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("class")
public class Classes {

	/*
	 * 设置属性显示
	 */
	@XStreamAsAttribute
	@XStreamAlias("名称")
	private String name;

	/*
	 * 忽略
	 */
	@XStreamOmitField
	private int number;

	@XStreamImplicit(itemFieldName = "Students")
	private List<Student> students;

	@SuppressWarnings("unused")
	@XStreamConverter(SingleValueCalendarConverter.class)
	private Calendar created = new GregorianCalendar();

	public Classes() {
	}

	public Classes(String name, Student... stu) {
		this.name = name;
		this.students = Arrays.asList(stu);
	}
	// getter、setter
}
