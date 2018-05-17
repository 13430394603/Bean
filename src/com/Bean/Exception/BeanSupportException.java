package com.Bean.Exception;
/**
 * 
 * <b>BeanSupport异常<b>
 * @see
 * 构造方法参数空异常
 * setter、getter方法找不到异常
 * BeanMethodSupport 获取首字母大写的属性字段时参数空异常
 * @author 威 
 * <br>2018年3月25日 下午6:02:49 
 *
 */
public class BeanSupportException extends Exception {

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 4965277720216641027L;
	public BeanSupportException(String msg){
		super(msg);
	}
}
