package com.control.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/test")
public class testcommon {

	@Value(value="${testvalue:45}")
	private String testvalue="23";
	
	@RequestMapping("gettestvalue")
	public String gettestvalue(){
		System.out.println("testvalue:"+testvalue);
		return "success";
	}
}
