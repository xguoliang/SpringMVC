package com.mongo.morphia.ourtest;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity
public class Children {
	@Id
	ObjectId id;
	
	String cname;
	String cage;




	



	public ObjectId getId() {
		return id;
	}



	public void setId(ObjectId id) {
		this.id = id;
	}



	public Children() {

	}



	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCage() {
		return cage;
	}

	public void setCage(String cage) {
		this.cage = cage;
	}

}
