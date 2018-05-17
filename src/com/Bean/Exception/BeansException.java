package com.Bean.Exception;
/**
 * 
 * <b>处理beans的一些异常<b>
 * @see
 * buildType 字符非法异常
 * @author 威 
 * <br>2018年3月25日 下午5:09:01 
 *
 */
public class BeansException extends Exception {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 3169443318109652525L;

	public BeansException(String msg){
		super(msg);
	}
}
