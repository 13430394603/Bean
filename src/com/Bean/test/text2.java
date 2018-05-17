package com.Bean.test;

import com.Bean.core.BeanConfigure;
import com.Bean.core.BeanContext;
/**
 * 
 * <b>测试整个bean项目<b>
 * @author 威 
 * <br>2018年4月1日 下午8:10:01 
 *
 */
public class text2 {
	public static void main(String[] args) throws Exception{
		BeanContext context = BeanConfigure.configure("cfg.xml");
		TestBean tb = (TestBean) context.getBean("testBean");
		tb.say();
	}
}
