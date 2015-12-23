package com.zh.utils.xml.sax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.zh.utils.xml.XmlDocument;

/**
 * @author Alexia
 * 
 * SAX ����XML�ĵ�
 */
public class SaxDemo implements XmlDocument {

    public void parserXml(String fileName) {
        SAXParserFactory saxfac = SAXParserFactory.newInstance();

        try {
            SAXParser saxparser = saxfac.newSAXParser();
            InputStream is = new FileInputStream(fileName);
            saxparser.parse(is, new MySAXHandler());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MySAXHandler extends DefaultHandler {
    boolean hasAttribute = false;
    Attributes attributes = null;

    public void startDocument() throws SAXException {
        // System.out.println("�ĵ���ʼ��ӡ��");
    }

    public void endDocument() throws SAXException {
        // System.out.println("�ĵ���ӡ������");
    }

    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        if (qName.equals("users")) {
            return;
        }
        if (qName.equals("user")) {
            return;
        }
        if (attributes.getLength() > 0) {
            this.attributes = attributes;
            this.hasAttribute = true;
        }
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (hasAttribute && (attributes != null)) {
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.print(attributes.getQName(0) + ":"
                        + attributes.getValue(0));
            }
        }
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        System.out.print(new String(ch, start, length));
    }
    
    public static void main(String[] args) {
    	SaxDemo demo = new SaxDemo();
		String fileName = "src\\main\\resources\\test.xml";
		demo.parserXml(fileName);
	}
}
