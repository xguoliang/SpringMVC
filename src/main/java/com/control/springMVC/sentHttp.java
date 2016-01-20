package com.control.springMVC;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class sentHttp extends basehttpsent {
	private String  FROM="FROM";
	private String  JSON="JSON";
	private String  TEXT="TEXT";
	private String  FILE="FILE";
	@RequestMapping("/sent")
	private String sentrequest(String url,Object params,String type){
		String result ;
		switch(type){
		case "FROM":
			JSONObject  jasonObject = JSONObject.fromObject(params);
			Map<String,String> map = (Map<String,String>)jasonObject;
			result=sendForm(url,map);
			break;
		case "JSON":
			result=sendJson(url,(String)params);
			break;
		case "TEXT":
			result=sendMultipart(url,(File)params);
			break;
		case "FILE":
			
			break;	
		default:	
			break;
		}
		
		return "";
	}
	
	
	private String sendForm(String url,Map<String,String> params) {
		List<NameValuePair> valuePairs = new LinkedList<>();
		for (String key:params.keySet()){
			valuePairs.add(new BasicNameValuePair(key, (String) params.get(key)));
		}
		
		return sendHttpPostRequest(url,valuePairs);
	}

	/**
	 * application/json
	 * 
	 * @return
	 */
	private String sendJson(String url,String json) {
		return sendHttpPostRequest(url,json);
	}
	
	/**
	 * text/xml
	 */
	private String sendText() {
		return "";
	}
	
	
	/**
	 * multipart/form-data
	 * @return
	 */
	public  String sendMultipart(String url,File file) {
		return sendHttpPostRequest(url,file);
	}
}
