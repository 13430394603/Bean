package com.Bean.doMain;

import java.util.HashMap;
import java.util.Map;

import com.Bean.Exception.BeansException;
import com.Bean.util.SetterGetter;

public class Beans {
	private Map<String, Bean> beans = new HashMap<String, Bean>();
	private String namespace;
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public void addBean(String id, Bean bean){
		
		beans.put(id, bean);
	}
	public Object getBean(String id) throws Exception{
		Bean bean = beans.get(id);
		//判断实例化方式
		String buildType = bean.getBuildType();
		Object instance = getInstance(bean, buildType);
		//判断有没有property需要注入
		if(bean.getPropertys().size() > 0){
			for(Map.Entry<String, Property> item : bean.getPropertys().entrySet()){
				Property pro = item.getValue();
				Object instancePro = getInstance(pro, pro.getBuildType());
				//反射操作 操作的对象instance； 操作的属性名为getKey； 操作后的值为instancePro
				SetterGetter.setProperty(instance, item.getKey(), instancePro);
			}
		}
		return instance;
	}
	/**
	 * 
	 * 根据buildType创建出一个实例对象 
	 * @see
	 * normal  
	 * 	一般创建模式，每一次都会new一个对象
	 * single
	 * 	单例模式，每一次都是同一个对象
	 * prototype
	 * 	原型模式
	 * @param object bean、property对象
	 * @param buildType 创建对象的方式
	 * @return
	 * @throws Exception
	 * Object
	 *
	 */
	private Object getInstance(BeansData object, String buildType)throws Exception {
		StringBuilder classPath = new StringBuilder((namespace.length() > 0 ? namespace+"." : "")).
				append(object.getClazzPath());
		Class<?> clazz = Class.forName(classPath.toString());
		Object instance = null;
		if(buildType == null || buildType.equals("normal")) 		//一般模式
			instance = clazz.newInstance();
		else if(buildType.equals("single")){ 						//单例模式
			Object o = object.getInstance();
			if(o != null) instance = o;
			else {
				instance = clazz.newInstance();
				object.setInstance(instance);
			}
		}else if(buildType.equals("prototype")){}
		else														//buildType异常数据
			throw new BeansException("buildType is illegal: " + buildType);
		return instance;
	}
	public Map<String, Bean> getBeans(){
		return beans;
	}
	public String toString(){
		return this.getClass().getSimpleName()+"[namespace-"+this.namespace+
				", beansSize-"+this.beans.size()+"]";
	}
}
