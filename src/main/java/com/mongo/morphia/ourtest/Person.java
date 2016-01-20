package com.mongo.morphia.ourtest;

import java.util.ArrayList;
import java.util.List;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.utils.IndexDirection;
import com.mongodb.DBObject;
@PersisunitAno("rui_zhan_springmvctest")
//@Entity
public class Person {
	@Id
	private String id;
	@Indexed(value=IndexDirection.ASC,name="PID",unique=true,dropDups=true)
	private String personid;//身份证id号
	private String name;
	private int age;
	@Embedded
	private Adress adress;
	@Reference("childrens")
	private  List<Children> children =  new ArrayList<Children>();
	
	public List<Children> getChildren() {
		return children;
	}
	public void setChildren(List<Children> children) {
		this.children = children;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Adress getAdress() {
		return adress;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
}
