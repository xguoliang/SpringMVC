package com.mongo.morphia.ourtest;

import com.google.code.morphia.annotations.Embedded;

@Embedded
public class Adress {
	private String no;
	private String info;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
