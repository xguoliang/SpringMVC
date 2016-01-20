package com.common.path;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;

public class urlPathUntil {
	/**
	 * 获取类clazz所在路径的URl
	 * @param clazz
	 * @return
	 */
	public URL getClassUrl(Class clazz){
		URL url=clazz.getResource("");
		return url;
	}
	
	

	/**
	 * 获取classpath的路径，即在bin文件夹的URL
	 * @param clazz
	 * @return
	 */
	public URL getClassUrlRoot(Class clazz){
		URL url=clazz.getResource("/");
		return url;
	}
	
	/**
	 * 获取文件filename的URL，文件必须与类clazz在同一路径下
	 * @param clazz
	 * @param filename
	 * @return
	 */
	public URL getClassUrlRoot(Class clazz,String filename){
		URL url=clazz.getResource(filename);
		return url;
	}
	
	
	/**
	 * 
	 * @param servletContext
	 * @param filename
	 * @return D:\工具\Tomcat-6.0\webapps\002_ext\   002_ext为web项目名称
	 */
	public URL geContextlRoot(ServletContext servletContext,String filename){
		URL url = null;
		try {
			url = servletContext.getResource("/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	
}
