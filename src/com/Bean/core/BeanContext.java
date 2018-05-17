package com.Bean.core;
import com.Bean.Exception.BeansException;
import com.Bean.doMain.Beans;
/**
 * 
 * <b>BeanContext bean的核心对象<b>
 * @author 威 
 * <br>2018年3月25日 下午6:09:07 
 *
 */
public class BeanContext {
	private Beans beans;
	public BeanContext(Beans beans){
		this.beans = beans;
	}
	public Object getBean(String id) throws Exception {
		if(id == null || id.equals("")){
			throw new BeansException("id is not allowted be null and empty:" + id);
		}
		return beans.getBean(id);
	}
	public Beans getBeans(){
		return this.beans;
	}
}
