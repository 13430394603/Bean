package com.Bean.doMain;

public class Property implements BeansData {
	private String clazzPath;
	private String buildType;
	private Object instance;
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
	@Override
	public String toString(){
		return this.getClass().getSimpleName()+"[clazzPath-"+this.clazzPath+
				", buildType-"+this.buildType+
				", instance-"+this.instance+"]";
	}
}
