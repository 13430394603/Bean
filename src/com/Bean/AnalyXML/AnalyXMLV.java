package com.Bean.AnalyXML;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.Bean.Exception.AnalyXMLException;
/**
 * 
 * <b>本程序中特定解析抽象类 作为父类/<b>
 * @author 威 
 * <br>2017年12月25日 下午4:54:13 
 *
 */
public abstract class AnalyXMLV {
	protected Document doc = null ;
	/**
	 * 
	 * 使用类路径加载配置文件
	 * @see
	 * @param confPath
	 * void
	 * @throws Exception 
	 *
	 */
	public void configure(String confPath) throws Exception{
		if(confPath == null || confPath.equals("")) 
			throw new AnalyXMLException("解析 xml文件时配置文件路径不能为null或者empty: " + confPath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance() ;
		DocumentBuilder builder ;
		builder = dbFactory.newDocumentBuilder() ;
		doc = builder.parse(new File(getClass().getClassLoader().getResource(confPath).toURI())) ; 
	}
	/**
	 * 
	 * 根据文件路径获取配置文件
	 * @see
	 * @param filePath
	 * void
	 *
	 */
	public void ConfigureOfFileSystem(String filePath) {}
	/**
	 * 
	 * 获取特定的继根节点之后的元素集 模板方法
	 * @see getConfObject() 调用
	 * @return
	 * List<Element>
	 *
	 */
	public abstract List<Element> targetElements() throws Exception;
	/**
	 * 
	 * 对某一对象装入数据 
	 * @see
	 * @param obj
	 * @return
	 * Object
	 * @throws Exception 
	 *
	 */
	public abstract Object putConfObject(Object obj) throws Exception;
	
	/**
	 * 
	 * 根据标签名获取元素集 模板方法 
	 * @see targetElements() 调用
	 * @param nodeName
	 * @return
	 * @throws Exception
	 * List<Element>
	 *
	 */
	public List<Element> getElementsByTagName(String nodeName) throws Exception {
		if(nodeName == null || nodeName.equals(""))
			throw new AnalyXMLException("解析 xml文件时根据标签名称查找元素时参数标签名不能为null或者empty: " + nodeName);
		NodeList nodes = doc.getElementsByTagName(nodeName);
		List<Element> elements = new ArrayList<Element>();
		int len = nodes.getLength();
		for(int i = 0; i < len; i ++){
			Node node = nodes.item(i);
			if(node.getNodeType() == 1)
				elements.add((Element) node);
		}
		return elements;
	}
}
