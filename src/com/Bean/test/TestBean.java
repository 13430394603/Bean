package com.Bean.test;

public class TestBean {
	private Param param;
	public void setParam(Param param) {
		this.param = param;
	}
	public void say(){
		param.say();
	}
}
