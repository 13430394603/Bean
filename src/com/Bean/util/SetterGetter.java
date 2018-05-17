package com.Bean.util;
import java.lang.reflect.Method;

public class SetterGetter {
	static BeanSupport beanSupport;
	public static void setProperty(Object object, String propertyName, Object value) throws Exception{
		beanSupport = new BeanSupport(object.getClass(), propertyName);
		Method method = beanSupport.getWriteMethod();
		method.invoke(object, value);
	}
	public static Object getProperty(Object object, String propertyName) throws Exception{
		beanSupport = new BeanSupport(object.getClass(), propertyName);
		Method method = beanSupport.getReadMethod();
		return method.invoke(object);
	}
}
