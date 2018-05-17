package com.Bean.doMain;

import java.util.HashMap;
import java.util.Map;


public class Bean implements BeansData {
	private String clazzPath;
	private String buildType;     //property/single/prototype
	private Object instance;
	private Map<String, Property> propertys = new HashMap<String, Property>();
	
	public String getClazzPath() {
		return clazzPath;
	}
	public void setClazzPath(String clazzPath) {
		this.clazzPath = clazzPath;
	}
	public String getBuildType() {
		return buildType;
	}
	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}
	public Object getInstance() {
		return instance;
	}
	public void setInstance(Object instance) {
		this.instance = instance;
	}
	public void addProperty(String id, Property pro){
		propertys.put(id, pro);
	}
	public Property getProperty(Integer id){
		return propertys.get(id);
	}
	public Map<String, Property> getPropertys(){
		return propertys;
	}
	@Override
	public String toString(){
		return this.getClass().getSimpleName()+"[clazzPath-"+this.clazzPath+
				", buildType-"+this.buildType+
				", instance-"+this.instance+
				", propertysSize-"+this.propertys.size()+"]";
	}
}
