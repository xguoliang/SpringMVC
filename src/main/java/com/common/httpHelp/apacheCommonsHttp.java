package com.common.httpHelp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//commons-httpclient-3.1的上传文件
public class apacheCommonsHttp {
	private final int HTTP_OK = 200;
	private Logger logger = LoggerFactory.getLogger( this.getClass() );
	
	/**
	 * Get请求
	 * @param url
	 * @return
	 */
	public String sendHttpRequestGet(String url){
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
	
	//通用Post请求方法头
	/**
	 * Post请求
	 * @param url
	 * @param headerMap
	 * @param paramsMap
	 * @return
	 */
	public String sendHttpRequestPost(String url, Map<String, String> headerMap,
			Map<String, String> paramsMap) {
		String message = "";
		String response = null;
		HttpClient http = new HttpClient();
		PostMethod post = null;
		String uri = url;
		try {
			post = new PostMethod(uri);
			post.getParams().setContentCharset("UTF-8");
			if (headerMap != null && !headerMap.isEmpty()) {
				for (String keySet : headerMap.keySet()) {
					post.addRequestHeader(keySet, headerMap.get(keySet));
				}
			}
			// 循环所有参数
			if (paramsMap != null && !paramsMap.isEmpty()) {
				for (String keySet : paramsMap.keySet()) {
					post.addParameter(keySet, paramsMap.get(keySet));
				}
			}
			// 发送消息
			http.executeMethod(post);
			System.out.print(post.getStatusCode());
			// 返回状态
			if (post.getStatusCode() == HttpStatus.SC_OK) {
//				//这个方法在有汉字的时候可能有乱码
//				response = new String(post.getResponseBodyAsString().getBytes(
//						"UTF-8"));

				//该方法避免乱码
				byte[] resultBytes = new byte[1024];
				InputStream in=post.getResponseBodyAsStream();
				in.read(resultBytes);
				String msgContent = new String(resultBytes, "UTF-8");
				response=msgContent;
				System.out.println(msgContent);
				
			} else {
				return "";
			}
			post.releaseConnection();
		} catch (Throwable e) {
			logger.info("发送信息到" + url + "失败，错误信息为：" + e.getMessage());
			return "";
		} finally {
			post.releaseConnection();
		}
		return response;
	}
	

	/**
	 * 上传文件方法
	 * @param uri
	 * @param file
	 * @return
	 */
	public String sendHttpPostRequest(String uri, File file) {
		logger.info( "发送http请求：url={},file={}", uri, file );
		PostMethod httpPost = new PostMethod(uri);
		try {
			Part[] parts = { new FilePart("file", file,	"application/octect-stream", "utf-8") };
			httpPost.setRequestEntity(new MultipartRequestEntity(parts,	httpPost.getParams()));
			org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			int status = httpClient.executeMethod(httpPost);
			if (status == HTTP_OK) {
				httpPost.getResponseBodyAsString();
			}
		} catch (FileNotFoundException e) {
			logger.error( "发送http请求失败,uri={}, exception={}", uri, e);
		} catch (IOException e) {
			logger.error( "发送http请求失败,uri={}, exception={}", uri, e);
		}
		return null;
	}
	
	
}
