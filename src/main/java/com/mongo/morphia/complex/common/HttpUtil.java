package com.mongo.morphia.complex.common;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
/**
 * 
 * @since 相关http相关工具类希望都写在这个地方
 * @author lian_lin
 *
 */
public class HttpUtil {
	 
	/**
	 * @param host 获取内容IP/域名地址
	 * @return 根据host返回http中body中的内容
	 * @throws Exception
	 */
	public static String getHttpRspBody(String host) throws Exception{
		return getHttpRspBody(host, "/");
	}
	

	/**
	 * @param host 获取内容IP/域名地址
	 * @param url 应用path路径
	 * @return 根据host url返回http中body中的内容
	 * @throws Exception
	 */
	 public static String getHttpRspBody(String host,String url) throws Exception{
		String result = "";
		if (StringUtils.isBlank(host) || StringUtils.isBlank(url)) {
			return result;
		}

		HttpClient client = new HttpClient();
//		client.getHostConfiguration().setHost(host);此时HOTS里面有端口号，则需要加上端口号
		if (host.indexOf(":")>0) {
			int port = Integer.parseInt(host.substring(host.indexOf(":")+1).toString());
			
			client.getHostConfiguration().setHost(host.substring(0,host.indexOf(":")),port);
		}else
		{
			client.getHostConfiguration().setHost(host);
		}
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
				"utf-8");
		// 设置超时时间
		client.getParams().setSoTimeout(6000);
		// 创建远程访问
		GetMethod method = new GetMethod(url);
		// 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// method.getParams().setParameter("userId", userId);
		// 执行getMethod
		int statusCode = client.executeMethod(method);
		if (statusCode != HttpStatus.SC_OK) {
			// logger.info(".......Method failed: " + method.getStatusLine());
			return result;
		}
		// 获取数据
		String responseBody = IOUtils.toString(
				method.getResponseBodyAsStream(), "UTF-8");
		return responseBody;
	}

	 public static String getHttpRspBody(String host,int port,String url) throws Exception
	 {
		 String result = "";
		 if (url == null) {
			 return result;
		 }

		 HttpClient client = new HttpClient();
		 client.getHostConfiguration().setHost(host, port);
		 
		 client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
					"utf-8");
		// 设置超时时间
		client.getParams().setSoTimeout(6000);
		// 创建远程访问
		GetMethod method = new GetMethod(url);
		// 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		// method.getParams().setParameter("userId", userId);
		// 执行getMethod
		int statusCode = client.executeMethod(method);
		if (statusCode != HttpStatus.SC_OK) {
			// logger.info(".......Method failed: " + method.getStatusLine());
			return result;
		}
		// 获取数据
		String responseBody = IOUtils.toString(
				method.getResponseBodyAsStream(), "UTF-8");
		return responseBody;
	}
		 
	public static void main(String[] args) {
		try{
		  //System.out.println(HttpUtil.getHttpRspBody("api.cmcloud.cn", "/api/portal.do?action=queryProduct&shelfId=7955925198&format=json"));
		  //System.out.println(HttpUtil.getHttpRspBody("www.baidu.com"));
		  
		  //System.out.println(HttpUtil.getHttpRspBody("xuntong.im", "/xuntong/groupUserOid.action?groupId=33a9afe1-44b8-45e5-8903-5c5ff9c7e284"));
		  
		
		 // System.out.println(HttpUtil.getHttpRspBody("172.20.137.183",8081,"/xtweb/web/kdweiboUnreads.do?token=dddd"));
		 
		String json="{\"name\":\"haha\",\"email\":\"bruce_sha@kingdee.com\",\"phone\":\"13715160773\"}";
		System.out.println(HttpUtil.sendJsonPostByHttpClient("imtest.kdweibo.cn", 80, "/openaccess/company/createforwb", json, null));
		  
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	public static String sendPostByHttpClient(String url,String paramStr) throws Exception
	{
		String body = "";
		
		HttpClient client = new HttpClient();
		
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
					"utf-8");
		// 设置超时时间
		client.getParams().setSoTimeout(6000);

		PostMethod httpost = new PostMethod(url);  
		
		
		// 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
		httpost.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		
		try {
			 JSONObject msg = new JSONObject();
			 msg = JSONObject.fromObject(paramStr);
			 
			 Set<String> keySet = msg.keySet();  
			 
			 if (keySet != null && !keySet.isEmpty()){
				 for(String key : keySet) {  
					 httpost.setParameter(key, String.valueOf(msg.get(key))); 
				}  
			 }
			 
			int statusCode = client.executeMethod(httpost);
			if(statusCode != HttpStatus.SC_OK){
				 return "";
			}
			 
			body = IOUtils.toString(
					httpost.getResponseBodyAsStream(), "UTF-8");
		}
		catch (Exception e){
			throw e;
		}
				 
		 return body;
	}
	

	public static String sendPostByHttpClientOnBody(String url,String paramStr) throws Exception
	{
		String body = "";
		
		HttpClient client = new HttpClient();
		
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
					"utf-8");
		// 设置超时时间
		client.getParams().setSoTimeout(6000);

		PostMethod httpost = new PostMethod(url);  
		
		
		// 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
		httpost.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		
		try {
			RequestEntity stringEntity=new StringRequestEntity(paramStr, "application/json", "utf-8");
			httpost.setRequestEntity(stringEntity); 
			 
			int statusCode = client.executeMethod(httpost);
			if(statusCode != HttpStatus.SC_OK){
				 return "";
			}
			 
			body = IOUtils.toString(
					httpost.getResponseBodyAsStream(), "UTF-8");
		}
		catch (Exception e){
			throw e;
		}
				 
		 return body;
	}
	
	public static String sendJsonPostByHttpClient(String host,int port,String url,String json,List<Header> headers) throws Exception
	{
		String body = "";		
		HttpClient client = new HttpClient();		
		client.getHostConfiguration().setHost(host, port);
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
					"utf-8");
		// 设置超时时间
		client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);  
		client.getHttpConnectionManager().getParams().setSoTimeout(10000); 
		client.getHttpConnectionManager().getParams().setSoTimeout(10000);  
		if(headers!=null&&headers.size()>0){
			client.getHostConfiguration().getParams().setParameter(  
	                "http.default-headers", headers); 
		}
		PostMethod httpost = new PostMethod(url);  
		
		// 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
		httpost.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		
		try {
			RequestEntity stringEntity=new StringRequestEntity(json, "application/json", "utf-8");
			httpost.setRequestEntity(stringEntity); 
			int statusCode = client.executeMethod(httpost);
			if(statusCode != HttpStatus.SC_OK){
				throw new RuntimeException(statusCode+"");
			}
			body = IOUtils.toString(
					httpost.getResponseBodyAsStream(), "UTF-8");
		}
		catch (Exception e){
			throw e;
		}
				 
		 return body;
	}
	
	/**
	 * 发送带JSON参数的http post请求
	 * @param strUrl
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendPost(String strUrl, String params) throws Exception
	{
		StringBuffer result = new StringBuffer();
		
		try{
			sendHttp(result,strUrl,params);
		}
		catch (ConnectException e){
			Thread.sleep(3000);
			
			sendHttp(result,strUrl,params);
		}
		catch (Exception e){
			throw e;
		}
		
		return result.toString();
	}
	
	private static String sendHttp(StringBuffer result,String strUrl,String params) throws Exception
	{
		HttpURLConnection connection = null;
		
		
		URL url = new URL(strUrl);// 创建连接
		connection = (HttpURLConnection) url  
                .openConnection();  
		
		connection.setDoOutput(true);  
        connection.setDoInput(true);  
        connection.setUseCaches(false);  
        connection.setInstanceFollowRedirects(true);  
        
        connection.setRequestMethod("POST"); // 设置请求方式  
        connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
        connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式  
        
        //设置超时链接时间
        connection.setConnectTimeout(6000);
        
        connection.connect();
        
        OutputStreamWriter out = new OutputStreamWriter(  
        		connection.getOutputStream(), "UTF-8"); // utf-8编码  
        
        out.append(params);
        out.flush();  
        out.close();
        
        int HttpResult = connection.getResponseCode();
        
        if(HttpResult ==HttpURLConnection.HTTP_OK){
            //读取响应
        	BufferedReader br = new BufferedReader(
        			new InputStreamReader(connection.getInputStream(),"utf-8"));  
            
        	String line = null;  
        	while ((line = br.readLine()) != null) {  
        		result.append(line + "\n");  
            }  

            br.close();  
        }
        
        return result.toString();
	}
	
}
