package com.Bean.test;

import java.lang.reflect.Method;
import com.Bean.util.BeanSupport;
/**
 * 
 * <b>测试BeanSupport<b>
 * @author 威 
 * <br>2018年3月25日 下午4:47:19 
 *
 */
public class test {
	public static void main(String[] args) throws Exception{ 
		Class<?> clazz = Class.forName("com.Bean.test.TestBean");
		TestBean tb = (TestBean) clazz.newInstance();
		BeanSupport beanSupport = new BeanSupport(clazz, "param");
		Method method = beanSupport.getWriteMethod();
		method.invoke(tb, new Param());
		Method readMethod = beanSupport.getReadMethod();
		System.out.println(readMethod.invoke(tb));
		tb.say();
	}
	
}

