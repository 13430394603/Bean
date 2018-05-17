package com.Bean.core;

import com.Bean.AnalyXML.Impl.AnalyXMLImpl;
import com.Bean.doMain.Beans;

public class BeanConfigure {
	public static BeanContext configure(String path) throws Exception{
		AnalyXMLImpl analyXMLImpl = new AnalyXMLImpl() ;
		analyXMLImpl.configure(path);
		Beans beans = (Beans) analyXMLImpl.getConfObject();
		BeanContext beanContext = new BeanContext(beans);
		return beanContext;
	}
}
