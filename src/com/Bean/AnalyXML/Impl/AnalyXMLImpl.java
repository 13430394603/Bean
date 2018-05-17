package com.Bean.AnalyXML.Impl;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;

import com.Bean.AnalyXML.AnalyXMLOfAll;
import com.Bean.doMain.Bean;
import com.Bean.doMain.Beans;
import com.Bean.doMain.Property;

public class AnalyXMLImpl extends AnalyXMLOfAll{
	@SuppressWarnings("unchecked")
	@Override
	public Object putConfObject(List<Map<String, Object>> e) {
		Beans beans = new Beans();
		for(Map<String, Object> map : e){
			Bean bean = new Bean();
			bean.setClazzPath((String) map.get("class"));
			bean.setBuildType((String) (map.get("buildType") == null ? "normal" : map.get("buildType")));
			List<Map<String, Object>> propertys = (List<Map<String, Object>>) map.get("propertys");
			if(propertys != null && propertys.size() > 0){
				Property pro = new Property();
				for(Map<String, Object> property : propertys){
					pro.setClazzPath((String) property.get("class"));
					pro.setBuildType((property.get("buildType") == null ? "normal" : (String) property.get("buildType")));
					bean.addProperty((String) property.get("id"), pro);
				}
			}
			beans.addBean((String) map.get("id"), bean);
		}
		return beans;
	}

	@Override
	public List<Element> targetElements() throws Exception {
		return getElementsByTagName("bean");
	}

	@Override
	public Object putConfObject(Object obj) throws Exception {
		Beans beans = (Beans) obj;
		List<Element> nodeList = getElementsByTagName("beans");
		beans.setNamespace(nodeList.get(0).getAttribute("namespace"));
		return beans;
	}
}
