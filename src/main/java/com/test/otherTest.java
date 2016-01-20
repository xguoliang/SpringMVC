package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import sun.misc.IOUtils;

import com.common.util.StringUtils;

public class otherTest {

	public static void main(String arg[]) {
//		String host = "kk.msbu.kingdee.com:81";
//		String uri = "/xuntong/groupUserOidInfo.action?groupId=274f5d4e-5d47-483c-9815-12fd8b867435";
//		String url=host+uri;
//		url="http://kk.msbu.kingdee.com:81/xuntong/groupUserOidInfo.action?groupId=274f5d4e-5d47-483c-9815-12fd8b867435";
//		otherTest.sendHttpRequestGet(url);	
		
		otherTest.httpsasd();

	}

	private static void httpsasd() {
		String host = "kk.msbu.kingdee.com";
//		int port =81;
		String url = "/xuntong/groupUserOidInfo.action?groupId=274f5d4e-5d47-483c-9815-12fd8b867435";
		String result = "";

		HttpClient client = new HttpClient();
//		client.getHostConfiguration().setHost(host);//如果有端口号，则此中用法就有问题
//		client.getHostConfiguration().setHost(host,port);
		
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
		int statusCode;
		try {
			statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				// logger.info(".......Method failed: " +
				// method.getStatusLine());
				System.out.println("出错！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 获取数据
		String responseBody="";
		try {
			responseBody = otherTest.convertStreamToString(method
					.getResponseBodyAsStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// String responseBody = IOUtils.toString(
		// method.getResponseBodyAsStream(), "UTF-8");
		System.out.println(responseBody);
	}

	public static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}
	public static String sendHttpRequestGet(String url){
		String response = null;
		//构造HttpClient的实例
		HttpClient httpClient = new HttpClient();

		//创建GET方法的实例
		GetMethod getMethod = new GetMethod(url);

//		//使用系统提供的默认的恢复策略
//		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
//		new DefaultHttpMethodRetryHandler());

		try {
			//执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus. SC_OK) {
				System.err.println("Method failed: "+ getMethod.getStatusLine());
			}
			//读取内容
			byte[] responseBody = getMethod.getResponseBody();
			//处理内容
			response=new String(responseBody);
			System.out.println(new String(responseBody));
		} catch (HttpException e) {
			//发生致命的异常，可能是协议不对或者返回的内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			//发生网络异常
			e.printStackTrace();
		} finally {
			//释放连接
			getMethod.releaseConnection();
		}	
		return response;
	}
}
