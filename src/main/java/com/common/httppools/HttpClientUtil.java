package com.common.httppools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpClientUtil {
	/**
	 * 日志处理类
	 */
	private static final Log log = LogFactory.getLog(HttpClientUtil.class);

	// 读取超时
	private final static int SOCKET_TIMEOUT = 10000;
	// 连接超时
	private final static int CONNECTION_TIMEOUT = 10000;
	// 每个HOST的最大连接数量
	private final static int MAX_CONN_PRE_HOST = 20;
	// 连接池的最大连接数
	private final static int MAX_CONN = 60;
	// 连接池
	private final static HttpConnectionManager httpConnectionManager;

	static {
		httpConnectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = httpConnectionManager.getParams();
		params.setConnectionTimeout(CONNECTION_TIMEOUT);
		params.setSoTimeout(SOCKET_TIMEOUT);
		params.setDefaultMaxConnectionsPerHost(MAX_CONN_PRE_HOST);
		params.setMaxTotalConnections(MAX_CONN);
	}

	/**
	 * 发送主要方法,异常捕获
	 * @param post
	 * @param code
	 * @return
	 */
	public static String doHttpRequest(PostMethod post, String code) {
		HttpClient httpClient = new HttpClient(httpConnectionManager);
		resetRequestHeader(httpClient, "10.0.23.178");
		// 设置读取超时时间(单位毫秒)
		// httpClient.getParams().setParameter("http.socket.timeout",socket_timeout);
		// 设置连接超时时间(单位毫秒)
		// httpClient.getParams().setParameter("http.connection.timeout",connection_timeout);
		// httpClient.getParams().setParameter("http.connection-manager.timeout",100000000L);
		BufferedReader in = null;
		String resultString = "";
		try {
			httpClient.executeMethod(post);
			in = new BufferedReader(new InputStreamReader(post
					.getResponseBodyAsStream(), code));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			resultString = buffer.toString();
		} catch (SocketTimeoutException e) {
			log.error("连接超时" + e.toString());
			// throw new CustomException(
			// TmallConstants.TmallExceptionType.CONNECTION_EXCEPTION,
			// "连接超时:" + e.getMessage(), e);
			resultString = returnError("连接超时");
		} catch (HttpException e) {
			log.error("读取外部服务器数据失败" + e.toString());
			// throw new CustomException(
			// TmallConstants.TmallExceptionType.SOCKET_EXCEPTION,
			// "读取天猫服务器数据失败:" + e.getMessage(), e);
			resultString = returnError("读取外部服务器数据失败");
		} catch (UnknownHostException e) {
			log.error("请求的主机地址无效" + e.toString());
			// throw new CustomException(
			// TmallConstants.TmallExceptionType.CONNECTION_EXCEPTION,
			// "请求的主机地址无效：" + e.getMessage(), e);
			resultString = returnError("请求的主机地址无效");
		} catch (IOException e) {
			log.error("向外部接口发送数据失败" + e.toString());
			// throw new CustomException(
			// TmallConstants.TmallExceptionType.INNER_EXCEPTION,
			// "向天猫发送数据失败", e);
			resultString = returnError("向外部接口发送数据失败");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			post.releaseConnection();
		}
		return resultString;
	}

	/**
	 * 设置一下返回错误的通用提示,可以自定义格式.
	 * @param reason
	 * @return
	 */
	public static String returnError(String reason) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		buffer.append("<Response>");
		buffer.append("<Success>false</Success>");
		buffer.append("<reason>");
		buffer.append(reason);
		buffer.append("</reason>");
		buffer.append("</Response>");
		return buffer.toString();
	}

	
	public final static String REQUEST_HEADER = "x-forwarded-for";
	/**
	 * 将客户IP写入请求头
	 * 这个设置可以伪装IP请求,注意使用
	 * @param client
	 * @param ip
	 * @return
	 */
	public static void resetRequestHeader(HttpClient client, String ip) {
		List<Header> headers = new ArrayList<Header>();
		headers.add(new Header(REQUEST_HEADER, ip));
		client.getHostConfiguration().getParams().setParameter(
				"http.default-headers", headers);
	}
}
