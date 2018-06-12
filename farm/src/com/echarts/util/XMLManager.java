package com.echarts.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLManager {
	
	/**
	 * ���xml�ĵ�����ķ�����
	 * @param uri
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document getDocument(String uri) throws ParserConfigurationException, SAXException, IOException{
		//1����ý�������������DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		//2������ĵ�������DocumentBuilder
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		//3������ĵ�����Document
		Document doc = db.parse(uri);
		return doc;
	}
	
	/**
	 * 1�������ĵ�����Document��ø��ڵ�
	 * 2��ͨ�����ڵ�����ӽڵ��б��ӽڵ�ļ��ϣ�
	 * 3��������ѯ�ӽڵ��б��������Ի��ÿ���ӽڵ��б�����ݣ�content��
	 * @return
	 */
	public static List<String> getChildElementContent(Document doc){
		List<String> childElementList = new ArrayList<String>();
		//1�������ĵ�����Document��ø��ڵ�
		Element rootElement = doc.getDocumentElement();
		//2��ͨ�����ڵ�����ӽڵ��б��ӽڵ�ļ��ϣ�,ͨ��*ͨ��������Դ������е��ӽڵ�����
		NodeList childElements = rootElement.getElementsByTagName("*");
		
		//3��������ѯ�ӽڵ��б��������Ի��ÿ���ӽڵ��б�����ݣ�content��
		for(int i = 0; i < childElements.getLength(); i ++){
			//�Ӽ����У�����ӽڵ��Ԫ�ض���
			Element childElement = (Element)childElements.item(i);
			
			//����ӽڵ������content
			String childElementContent = childElement.getTextContent();
			
			childElementList.add(childElementContent);
		}
		return childElementList;
	}
}
