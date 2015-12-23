package com.zh.utils.xml.dom4j;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zh.utils.xml.XmlDocument;

/**
 * @author Alexia
 * 
 * Dom4j ����XML�ĵ�
 */
public class Dom4jDemo implements XmlDocument {

    public void parserXml(String fileName) {
        File inputXml = new File(fileName);
        SAXReader saxReader = new SAXReader();

        try {
            Document document = saxReader.read(inputXml);
            Element users = document.getRootElement();
            for (Iterator i = users.elementIterator(); i.hasNext();) {
                Element user = (Element) i.next();
                for (Iterator j = user.elementIterator(); j.hasNext();) {
                    Element node = (Element) j.next();
                    System.out.println(node.getName() + ":" + node.getText());
                }
                System.out.println();
            }
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
    	Dom4jDemo demo = new Dom4jDemo();
		String fileName = "src\\main\\resources\\test.xml";
		demo.parserXml(fileName);
	}
}