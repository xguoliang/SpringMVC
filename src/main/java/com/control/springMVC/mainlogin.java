package com.control.springMVC;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// 类似Struts的Action
@RequestMapping("/mainlogin")
public class mainlogin {
	@RequestMapping("/test/login")
	public String testLogin() {
		return "login";
	}

	// 1.必须传入参数username
	@RequestMapping("test/login3")
	// 请求url地址映射，类似Struts的action-mapping
	public String testLogin3(@RequestParam(value = "username") String username,
			String password, HttpServletRequest request) {
		// @RequestParam是指请求url地址映射中必须含有的参数(除非属性required=false)
		// @RequestParam可简写为：@RequestParam("username")

		return username;
	}

	@RequestMapping("/test/return")
	@ResponseBody
	public void testReturn(HttpServletRequest request,
			HttpServletResponse response) {
		boolean jsonP = false;
		String cb = request.getParameter("callback");
		if (cb != null) jsonP = true;
		String res = "{\"respCmd\": \"1111\"}";
		Writer out;
		try {
			out = response.getWriter();
			
			if (jsonP) {
				out.write(cb + "(");
			}
			out.write(res);
			if (jsonP) {
				out.write(");");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if (cb != null) {
//			jsonP = true;
//			response.setContentType("text/javascript");
//		} else {
//			response.setContentType("application/x-json");
//		}
	}

}
