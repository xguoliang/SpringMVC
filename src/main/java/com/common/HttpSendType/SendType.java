package com.common.HttpSendType;

import java.util.HashMap;
import java.util.Map;

/***
 * 发送请求方式
 * 
 * @author Administrator
 * 
 */
public class SendType {
	/**
	 * application/x-www-form-urlencoded ontent-Type 被指定为
	 * application/x-www-form-urlencoded；其次，提交的数据按照 key1=val1&key2=val2
	 * 的方式进行编码，key 和 val 都进行了 URL 转码。大部分服务端语言都对这种方式有很好的支持。例如 PHP
	 * 中，$_POST['title'] 可以获取到 title 的值，$_POST['sub'] 可以得到 sub 数组。很多时候，我们用 Ajax
	 * 提交数据时，也是使用这种方式。例如 JQuery 和 QWrap 的 Ajax，Content-Type
	 * 默认值都是「application/x-www-form-urlencoded;charset=utf-8」。
	 * 
	 * @return
	 */
	public static String sendForm() {

		return "";
	}

	/**
	 * application/json
	 * 
	 * @return
	 */
	public static String sendJson() {
		Map<String,String> headMap = new HashMap<String,String>();
		headMap.put("Content-Type","application/x-www-form-urlencoded");
		return "";
	}
	
	/**
	 * text/xml
	 */
	public static String sendText() {
		return "";
	}
	
	
	/**
	 * multipart/form-data
	 * @return
	 */
	public static String sendMultipart() {
		return "";
	}
	
}
