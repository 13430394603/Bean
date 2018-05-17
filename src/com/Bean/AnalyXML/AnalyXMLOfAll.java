package com.Bean.AnalyXML;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
/**
 * 
 * <b>一句话描述该类<b>
 * @see
 * 标签的text改成value值
 * @author 威 
 * <br>2018年3月31日 下午9:56:19 
 *
 */
public abstract class AnalyXMLOfAll extends AnalyXMLV{
	public AnalyXMLOfAll(){}
	/**
	 * 
	 * 解析xml并装入特定对象 
	 * @see
	 * @return	返回特定对象 在putConfObject中处理
	 * Object
	 * @throws Exception
	 *
	 */
	public Object getConfObject() throws Exception{
		List<Element> elements = targetElements(); //获取主要元素集
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>() ;
		Map<String, Object> maps;
		int len = elements.size();
		for(int i = 0; i < len; i ++){
			maps = new HashMap<String, Object>();
			Element element = elements.get(i);
			dealAttrs(element.getAttributes(), maps); //解析attrs
			//获取bean后继的property标签的属性
			NodeList nodelist = element.getElementsByTagName("property");
			int len_ = nodelist.getLength();
			if(len_>0){
				List<Map<String, Object>> propertys = new ArrayList<Map<String, Object>>();
				Map<String, Object> property;
				for(int j = 0; j < len_; j++){
					property = new HashMap<String, Object>();
					dealAttrs(nodelist.item(j).getAttributes(), property); //解析attrs
					propertys.add(property);
				}
				maps.put("propertys", propertys);
			}
			lists.add(maps);
		}
		return putConfObject(putConfObject(lists));
	}
	/**
	 * 
	 * 在属性NamedNodeMap 中通过解析将每一个属性转入map中
	 * @see
	 * @param attrMaps
	 * @param maps
	 * void
	 *
	 */
	public void dealAttrs(NamedNodeMap attrMaps, Map<String, Object> maps){
		int attrLen = attrMaps.getLength();
		for(int i = 0; i < attrLen; i++){
			String attr = attrMaps.item(i).toString(); //得到的数据是 key=value
			int sepIndex = attr.indexOf("=");
			String value = attr.substring(sepIndex + 1, attr.length());
			maps.put(attr.substring(0, sepIndex), getType(value));
		}
	}
	/**
	 * 
	 * 将属性值赋予相应的类型
	 * @see
	 * 输入参数有可能
	 *	boolean字符串
	 * 	数字字符串
	 * 	"xxx"带上引号的字符串
	 * @param value
	 * @return
	 * Object
	 *
	 */
	public Object getType(String value){
		if(value.indexOf("\"") != -1)
			return value.replaceAll("\"", "");
		else if(value.equals("false") || value.equals("true"))
			return Boolean.parseBoolean(value);
		else
			return Integer.parseInt(value);
	}
	/**
	 * 
	 * 传入集合数据装入特定对象
	 * @see
	 *  此方法有重载 还有一个方法是补充特定对象的数据
	 * @param e
	 * @return
	 * Object
	 *
	 */
	public abstract Object putConfObject(List<Map<String, Object>> e);
}

