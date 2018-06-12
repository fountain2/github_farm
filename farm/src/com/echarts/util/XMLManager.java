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
	 * 获得xml文档对象的方法。
	 * @param uri
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document getDocument(String uri) throws ParserConfigurationException, SAXException, IOException{
		//1、获得解释器工厂对象DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		//2、获得文档解析器DocumentBuilder
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		//3、获得文档对象Document
		Document doc = db.parse(uri);
		return doc;
	}
	
	/**
	 * 1、根据文档对象Document获得根节点
	 * 2、通过根节点或者子节点列表（子节点的集合）
	 * 3、遍历查询子节点列表，进而可以获得每个子节点列表的内容（content）
	 * @return
	 */
	public static List<String> getChildElementContent(Document doc){
		List<String> childElementList = new ArrayList<String>();
		//1、根据文档对象Document获得根节点
		Element rootElement = doc.getDocumentElement();
		//2、通过根节点或者子节点列表（子节点的集合）,通过*通配符，可以代表所有的子节点名称
		NodeList childElements = rootElement.getElementsByTagName("*");
		
		//3、遍历查询子节点列表，进而可以获得每个子节点列表的内容（content）
		for(int i = 0; i < childElements.getLength(); i ++){
			//从集合中，获得子节点的元素对象
			Element childElement = (Element)childElements.item(i);
			
			//获得子节点的内容content
			String childElementContent = childElement.getTextContent();
			
			childElementList.add(childElementContent);
		}
		return childElementList;
	}
}
