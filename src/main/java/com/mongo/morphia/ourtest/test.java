package com.mongo.morphia.ourtest;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.Key;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.mapping.MappedClass;
import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryImpl;
import com.mongo.morphia.simaple.model.Band;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class test {
	private static Datastore ds;
	@Test
	public void create() {
		Person person = new Person();
		person.setName("test");
		person.setAge(20);
		person.setPersonid("00001");

		Adress adress = new Adress();
		adress.setInfo("广东省深圳市");
		adress.setNo("2");
		// ds.save(adress);
		person.setAdress(adress);

		List<Children> childrens = new ArrayList<Children>();
		Children children = new Children();
		children.setCage("2");
		children.setCname("小兰");
		// children.setId("01");
		ds.save(children);

		childrens.add(children);
		// person.getChildren().add(children);

		children = new Children();
		children.setCage("3");
		children.setCname("小红");
		ds.save(children);

		childrens.add(children);

		person.setChildren(childrens);

		// person.getChildren().add(children);

		Key<Person> personkey = ds.save(person);

		Query query = ds.createQuery(Person.class).filter("name = ", "test");

		Person result2 = (Person) query.asList().get(0);

		String id = (String) personkey.getId();

		QueryImpl<Person> query2 = new QueryImpl<Person>(Person.class,
				ds.getCollection(Person.class), ds);

		// Query<Person> query2 =ds.createQuery(Person.class);

		List<Person> pesonList = query.filter("name = ", "test").asList();
		for (Person p : pesonList) {
			System.out.println(until.tostring(p));
		}
		System.out.println("完成！");

	}

	@Before
	public void init() {

		String host = "192.168.22.209";
		int port = 27017;
		Mongo mongo = null;
		try {
			mongo = new Mongo(host, port);
//			MongoClient mongoClient =new MongoClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("初始化mogondb出错" + e);
		}

		Morphia morphia = new Morphia();
		String packageName = "com.mongo.morphia.ourtest";
		morphia.mapPackage(packageName);
		Mapper mapper = morphia.getMapper();

		// MappedClass mappedclass2 =mapper.getMappedClass(Children.class);
		// System.out.println(mappedclass2.getCollectionName());
		//
		MappedClass mappedclass = mapper.addMappedClass(Person.class);
		// System.out.println(mappedclass.getCollectionName());
		//
		// String tablename="T_"+"Person";
		// mappedclass.setCollectionName(tablename);

		// System.out.println(mappedclass.getCollectionName());
		String dbName = "rui_zhan_springmvctest";
		ds = new DatastoreImpl(morphia, mongo, dbName);
	}

	@Test
	public void find() {
		try {
			Query<Person> query = ds.createQuery(Person.class).filter("name = ", "test");
			Person result = (Person) query.asList().get(0);
			System.out.println(until.tostring(result));
		} catch (Exception e) {
			System.out.println(e);
		}
//        Query<Band> query = datastore.createQuery(Band.class).filter("name = ", "Love Burger");
//        Band result = (Band) query.asList().get(0);
	}
}
