package com.common.path;

import java.io.InputStream;

public class inputPathUntil {
	
	/**
	 *  获取文件filename的URL，文件必须与类clazz在同一路径下
	 * @param clazz
	 * @return
	 */
	public InputStream getClassStream(Class clazz,String filename){
		InputStream in =clazz.getResourceAsStream(filename);
		return in;
	}
	
	
	/**
	 *  文件filename的格式如下 “/com/explorers/abc.jpg”
	 * @param clazz
	 * @return
	 */
	public InputStream getClassStream2(Class clazz,String filename){
		InputStream in =clazz.getResourceAsStream(filename);
		return in;
	}
	
	
	/**
	 *文件filename的格式如下 “com/explorers/abc.jpg”
	 * @param clazz
	 * @return
	 */
	public InputStream getClassStream3(Class clazz,String filename){
		InputStream in =clazz.getClassLoader().getResourceAsStream(filename);
		return in;
	}
	
	
	/**
	 *文件filename的格式如下 “com/explorers/abc.jpg”
	 * @param clazz
	 * @return
	 */
	public InputStream getClassLoadStream(String filename){
		InputStream in = ClassLoader.getSystemResourceAsStream(filename);
		return in;
	}
}
