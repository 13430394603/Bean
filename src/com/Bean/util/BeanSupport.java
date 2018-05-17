
package com.Bean.util;

import java.lang.reflect.Method;

import com.Bean.Exception.BeanSupportException;
/**
 * 
 * <b>javaBean的一个支持类<b>
 * @see
 * 作用
 *  找到对应的getter、setter方法
 * 特别说明
 *  基本类型的boolean布尔值 其方法为isMethod() 类中有解决的方案 
 *  集合中的写入方法add 类中有解决方案
 *  以上提到的本类否会自动处理只需要正常调用就行
 * @author 威 
 * <br>2018年3月25日 下午4:34:01 
 *
 */
public class BeanSupport {
	private Class<?> beanClass;
	private String propertyName;
	private String writeMethodName;
	private String readMethodName;
	private Object param;
	public BeanSupport(Class<?> beanClass, String propertyName) throws Exception{
		this(beanClass, propertyName, null);
	}
	public BeanSupport(Class<?> beanClass, String propertyName, Object param) throws Exception{
		if(beanClass == null){
			throw new BeanSupportException("beanClass is not allowed to be null:" + beanClass);
		}else if(propertyName == null || propertyName.equals("")){
			throw new BeanSupportException("propertyName is not allowed to be null and empty:" + propertyName);
		}
		this.writeMethodName = BeanMethodSupport.SET_PFIX + BeanMethodSupport.capitalLetter(propertyName);
		this.readMethodName = BeanMethodSupport.GET_PFIX + BeanMethodSupport.capitalLetter(propertyName);
		this.propertyName = propertyName;
		this.beanClass = beanClass;
		this.param = param;
	}
	/**
	 * 
	 * 获取一个写入方法的实例化对象 
	 * @see
	 * 
	 * 调用构造 BeanSupport(Class<?> beanClass, String propertyName, Object param) 只能用此构造<br>
	 * 得到的实例已经对set方法注入了参数，而注入的参数从上面的构造方法中得来
	 * @return
	 * @throws Exception
	 * Object
	 *
	 */
	public Object getWriteInstance() throws Exception{
		if(param == null){
			throw new BeanSupportException("param is not allowed to be null:" + param);
		}
		Object object = beanClass.newInstance();
		Method method = getWriteMethod();
		method.invoke(object, this.param);
		return object;
	}
	/**
	 * 
	 * 在获取读方法或者读方法的实例对象时使用的辅助方法，意图是找出正确的写方法即set方法
	 * @see
	 * @param beanClass
	 * @param methodName
	 * @return
	 * Method
	 *
	 */
	private Method getWriteMethod(Class<?> beanClass, String methodName){
		Method[] methods = beanClass.getDeclaredMethods();
		for(Method method : methods){
			if(method.getName().equals(methodName)) return method;
		}
		return null;
	}
	/**
	 * 
	 * 这里用一句话描述这个方法的作用 
	 * @see
	 * @return
	 * @throws BeanMethodSupportException
	 * @throws BeanSupportException
	 * Method
	 *
	 */
	public Method getWriteMethod() throws BeanSupportException {
		Method method = getWriteMethod(beanClass, this.writeMethodName);
		if(method == null){
			this.writeMethodName = BeanMethodSupport.ADD_PFIX + BeanMethodSupport.capitalLetter(this.propertyName);
			method = getWriteMethod(beanClass, this.writeMethodName);
			if(method == null) throw new BeanSupportException("Method not found: " + this.writeMethodName);
		}
		return method;
	}
	
	/**
	 * 
	 * 获取get方法
	 * @see
	 * @return
	 * @throws Exception
	 * Method
	 *
	 */
	public Method getReadMethod() throws Exception{
		Method method;
		try{
			method = beanClass.getDeclaredMethod(this.readMethodName);
		}catch(NoSuchMethodException e){
			this.readMethodName = BeanMethodSupport.IS_PFIX + BeanMethodSupport.capitalLetter(this.propertyName);
			try{
			method = beanClass.getDeclaredMethod(this.readMethodName);
			}catch(NoSuchMethodException ee){
				throw new BeanSupportException("Method not found: " + this.readMethodName);
			}
		}
		return method;
	}
}
