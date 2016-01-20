package com.control.springMVC;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.common.base.imp.BaseController;
import com.model.User;
import com.server.LoginService;

@RequestMapping("/send")
public class acceptHttp extends BaseController {
	@RequestMapping("/accept1")
	public String testAccept1(User user) {
		// 同样支持参数为表单对象，类似于Struts的ActionForm，User不需要任何配置，直接写即可
		String username = user.getUsername();
		String password = user.getPassword();
		int age = user.getAge();

		Map<String, Object> map = new HashMap<String, Object>();
		return SUCCESS(map);

	}

	@RequestMapping(value = "/accept2", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String testAccept2(String username, String password) {
		// request和response不必非要出现在方法中，如果用不上的话可以去掉
		// 参数的名称是与页面控件的name相匹配，参数类型会自动被转换
		return "";
	}
	
	@RequestMapping("accept3")	// 请求url地址映射，类似Struts的action-mapping
	public String testAccept3(String username, String password,
			HttpServletRequest request) {

		String result = getResponse();

		return "";
	}

//	RequestMapping注解有六个属性，下面我们把她分成三类进行说明。
//	1、 value， method；
//	value：     指定请求的实际地址，指定的地址可以是URI Template 模式（后面将会说明）；
//	method：  指定请求的method类型， GET、POST、PUT、DELETE等；
//
//	2、 consumes，produces；
//	consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
//	produces:    指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
//
//	3、 params，headers；
//	params： 指定request中必须包含某些参数值是，才让该方法处理。
//	headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求。
	// 1.必须传入参数username


	
	/**
	 * 2、 consumes，produces；
		consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
		produces:    指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
	 * @param petId
	 * @return
	 */
	//方法仅处理request请求中Accept头中包含了"application/json"的请求，同时暗示了返回的内容类型为application/json;
	@RequestMapping(value = "/accept4/{petId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String testAccept4(@PathVariable String petId) {
		// implementation omitted
		Map<String, Object> map = new HashMap<String, Object>();
		return SUCCESS(map);
	}
	

	//params： 指定request中必须包含某些参数值是，才让该方法处理。
	//仅处理请求中包含了名为“myParam”，值为“myValue”的请求；
	@RequestMapping(value = "/accept5/{petId}", method = RequestMethod.GET, params = "myParam=myValue")
	public String testAccept5(@PathVariable String ownerId,
			@PathVariable String petId) {
		Map<String, Object> map = new HashMap<String, Object>();
		return SUCCESS(map);
	}

	//headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求。
	//仅处理request的header中包含了指定“Refer”请求头和对应值为“http://www.ifeng.com/”的请求；
	@RequestMapping(value = "/pets", method = RequestMethod.GET, headers="Referer=http://www.test.com/")  
	  public String findPet(@PathVariable String ownerId, @PathVariable String petId) {      
		Map<String, Object> map = new HashMap<String, Object>();
		return SUCCESS(map);
	  }  
	
	private String getResponse() {

		return "";
	}

}
