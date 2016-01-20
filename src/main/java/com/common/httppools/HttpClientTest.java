package com.common.httppools;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * 组装参数,这里用Map,一键一值比较通用,可以当做共用方法
 * 
 * @param params
 * @return
 */
public class HttpClientTest {
	private NameValuePair[] buildNameValuePairs(Map<String, String> params) {
		Object[] keys = params.keySet().toArray();
		NameValuePair[] pairs = new NameValuePair[keys.length];

		for (int i = 0; i < keys.length; i++) {
			String key = (String) keys[i];
			pairs[i] = new NameValuePair(key, params.get(key));
		}

		return pairs;
	}

	/**
	 * 发送HTTP请求
	 * 
	 * @param bean
	 * @return
	 */
	public String lscmToOutsideService(Map<String, Object> bean) {

		String url = (String) bean.get("Url");
		HashMap<String, String> params = (HashMap<String, String>) bean
				.get("Params");
		String code = (String) bean.get("Encode");
		if (null == url || "".equals(url.trim())) {
			return HttpClientUtil.returnError("推送地址错误[url=" + url + "]");
		}
		if (null == code || "".equals(code.trim())) {
			return HttpClientUtil.returnError("编码错误[code=" + code + "]");
		}

		if (null == params || params.size() == 0) {
			return HttpClientUtil.returnError("无传递参数[params.size = 0]");
		}
		NameValuePair[] pairs = buildNameValuePairs(params);// 组装参数
		PostMethod postMethod = new PostMethod(url);// 放地址
		postMethod.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=" + code);
		postMethod.setRequestBody(pairs);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());

		return HttpClientUtil.doHttpRequest(postMethod, code);
	}

	/**
	 * 测试一下
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String url = "http://10.0.2.241:8080/xxx/XxxServlet";

		String xml = "xxxxxxxxxxxxxxxxxxxxxxx";
		// PushData bean = new PushData();
		Map bean = new HashMap<String, Object>();

		bean.put("Encode", "UTF-8");
		// bean.setEncode("UTF-8");
		// bean.setUrl(url);
		bean.put("Url", url);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("aaaa", xml);
		params.put("bbbb", "bbbxxxx");
		// bean.setParams(params);
		bean.put("Params", params);
		String r = new HttpClientTest().lscmToOutsideService(bean);
		System.out.println(r);

	}
}