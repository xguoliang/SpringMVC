package com.mongo.GridFS;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import com.common.comfig.loadConfig;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
/**
 * 用java驱动上传下载文件：
下载地址：https://github.com/mongodb/mongo-java-driver/downloads
以下代码基于mongo-2.7.3.jar
 * @author Administrator
 *
 */
public class GridFSDB {
	    Mongo connection;
	    DB db;
	    DBCollection collection;
	     GridFS myFS;
	 
	    String mongoDBHost = loadConfig.getMongoUrl();
	    int mongoDBPort = Integer.parseInt(loadConfig.getMongoPort());
	    String dbName = loadConfig.getMongoDbname();
	    String collectionName = "fs";
	 
	    public static void main(String[] args) throws MongoException, IOException, NoSuchAlgorithmException {
	    	GridFSDB t = new GridFSDB();
	         
//	        String fileName = "C:/1/gridfs.jpeg";
	        String fileName ="C:/1/temp.txt";
//	        String name = "gridfs.jpeg";
	        String name = "temp.txt";
	         
	        //把文件保存到gridfs中，并以文件的md5值为id
	        t.save(new FileInputStream(new File(fileName)), name);
	         
	        //据文件名从gridfs中读取到文件
	         System.out.println(t.getByFileName(name));
	         System.out.println(t.getById(name));
;	        GridFSDBFile gridFSDBFile = t.getByFileName(name);
	        if(gridFSDBFile != null){
	            System.out.println("filename:" + gridFSDBFile.getFilename());
	            System.out.println("md5:" + gridFSDBFile.getMD5());
	            System.out.println("length:" + gridFSDBFile.getLength());
	            System.out.println("uploadDate:" + gridFSDBFile.getUploadDate());
	             
	            System.out.println("--------------------------------------");
	            gridFSDBFile.writeTo(System.out);//直接输出文件内容,汉子可能会乱码，如果需要获取内容可通过输入流
	            gridFSDBFile.writeTo("C:/2/temp.txt");//直接输出文件
	        }else{
	            System.out.println("can not get file by name:" + name);
	        }
	        
//            DBCursor cursor = myFS.getFileList();  
//            while(cursor.hasNext()){  
//                System.out.println(cursor.next());  
//            }   
	    }
	 
	    public GridFSDB() throws UnknownHostException, MongoException, NoSuchAlgorithmException {
	        _init();
	    }
	     
	 
	    public GridFSDB(String mongoDBHost, int mongoDBPort, String dbName,
	            String collectionName) throws UnknownHostException, MongoException, NoSuchAlgorithmException {
	        this.mongoDBHost = mongoDBHost;
	        this.mongoDBPort = mongoDBPort;
	        this.dbName = dbName;
	        this.collectionName = collectionName;
	        _init();
	    }
	     
	     
	    private void _init() throws UnknownHostException, MongoException, NoSuchAlgorithmException{
	        connection = new Mongo(mongoDBHost, mongoDBPort);
	        db = connection.getDB(dbName);
	        collection = db.getCollection(collectionName);
	        myFS = new GridFS(db);
	    }
	     
	    /**
	     * 用给出的id，保存文件，透明处理已存在的情况
	     * id 可以是string，long，int，org.bson.types.ObjectId 类型
	     * @param in
	     * @param id
	     */
	    public void save(InputStream in, String FileName){
	        DBObject query  = new BasicDBObject("filename", FileName);
	        GridFSDBFile gridFSDBFile = myFS.findOne(query);
	         
	        if(gridFSDBFile != null)
	            return;
	         
	        GridFSInputFile gridFSInputFile = myFS.createFile(in,FileName);
	        gridFSInputFile.setContentType("type/json");
	        gridFSInputFile.save();
	        System.out.println(gridFSInputFile.getId());
	        System.out.println(gridFSInputFile.getFilename());
	        return;
	    }
	     
	    /**
	     * 据id返回文件
	     * @param id
	     * @return
	     */
	    public GridFSDBFile getById(Object id){
	        DBObject query  = new BasicDBObject("_id", id);
	        GridFSDBFile gridFSDBFile = myFS.findOne(query);
	        return gridFSDBFile;
	    }
	     
	    /**
	     * 据文件名返回文件，只返回第一个
	     * @param fileName
	     * @return
	     */
	    public GridFSDBFile getByFileName(String fileName){
	        DBObject query  = new BasicDBObject("filename", fileName);
	        GridFSDBFile gridFSDBFile = myFS.findOne(query);
	        return gridFSDBFile;
	    }
	
}
