package com.mongo.morphia.ourtest;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

public class DatastoreHelp extends DatastoreImpl {

	public DatastoreHelp(Morphia morphia, Mongo mongo) {
		super(morphia, mongo);
		// TODO Auto-generated constructor stub
	}
	private static Datastore ds;

}
