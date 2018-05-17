package com.Bean.util;

import com.Bean.Exception.BeanSupportException;
/**
 * 
 * <b>提供bean对象的前缀和属性大写构成标准的bean对象方法<b>
 * @author 威 
 * <br>2018年3月25日 下午4:38:02 
 *
 */
public class BeanMethodSupport{
	public static final String IS_PFIX = "is";
	public static final String SET_PFIX = "set";
	public static final String ADD_PFIX = "add";
	public static final String GET_PFIX = "get";
	//首字母大写
	public static String capitalLetter(String name) throws BeanSupportException{
		if(name == null || name.equals("")){
			throw new BeanSupportException("propertyName is not allowed to be null and empty: " + name);
		}
		return name.substring(0, 1).toUpperCase() + name.substring(1); 
	}
}
