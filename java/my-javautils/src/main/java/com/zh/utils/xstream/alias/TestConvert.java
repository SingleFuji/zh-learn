package com.zh.utils.xstream.alias;

import com.thoughtworks.xstream.XStream; 

import java.util.List; 
import java.util.ArrayList; 

/** 
* Created by IntelliJ IDEA.<br> 
* <b>User</b>: leizhimin<br> 
* <b>Date</b>: 2008-5-21 10:54:05<br> 
* <b>Note</b>: 测试 
*/ 
public class TestConvert { 

    public static void main(String args[]) { 
        test(); 
    } 

    public static void test() { 
        System.out.println("----------test()----------"); 
        Investor investor1 = new Investor(1, "gaici"); 
        Investor investor2 = new Investor(2, "hahhah", 33); 
        List<Investor> investorList = new ArrayList<Investor>(); 
        investorList.add(investor1); 
        investorList.add(investor2); 
        MainBody mainBody = new MainBody(1000, "微软", investorList); 

        XStream xStream = new XStream(); 

        /************** 别名定义 Object －> xml ***************/ 

        //没有做任何别名定义直接转换 
        outXML(1, xStream, mainBody); 

        //类别名 
        xStream.alias("MainBody", MainBody.class); 
        xStream.alias("Investor", Investor.class); 
        outXML(2, xStream, mainBody); 

        //列表节点别名 
        xStream.aliasField("Investor-List", MainBody.class, "investorList"); 
        outXML(3, xStream, mainBody); 

        //一般属性节点别名 
        xStream.aliasField("ztbs", MainBody.class, "id"); 
        xStream.aliasField("gdbs", Investor.class, "id"); 
        outXML(4, xStream, mainBody); 

        //将name成员作为属性添加到Investor对应xml节点里 
//        xStream.aliasAttribute(Investor.class,"name","GDXM"); 
        xStream.useAttributeFor(Investor.class, "name"); 
        outXML(5, xStream, mainBody); 

        xStream.aliasAttribute(Investor.class, "age", "NL"); 
        xStream.useAttributeFor(Investor.class, "age"); 
        outXML(6, xStream, mainBody); 

        /***********************xml －> Object*************************/ 
        String newxml = "<MainBody>\n" + 
                "  <ztbs>1000</ztbs>\n" + 
                "  <name>微软</name>\n" + 
                "  <Investor-List>\n" + 
                "    <Investor name=\"gaici\" NL=\"0\">\n" + 
                "      <gdbs>1</gdbs>\n" + 
                "    </Investor>\n" + 
                "    <Investor name=\"hahhah\" NL=\"33\">\n" + 
                "      <gdbs>2</gdbs>\n" + 
                "    </Investor>\n" + 
                "  </Investor-List>\n" + 
                "</MainBody>"; 
        MainBody m = (MainBody) xStream.fromXML(newxml); 
        System.out.println("将最后一次所得的xml结果转换为Java对象输出："); 
        System.out.println(m); 
    } 

    public static void outXML(int index, XStream xStream, MainBody m) { 
        String xml = xStream.toXML(m); 
        System.out.println(">>>>>>>>>>第" + index + "次输出XML:"); 
        System.out.println(xml + "\n"); 
    } 
}