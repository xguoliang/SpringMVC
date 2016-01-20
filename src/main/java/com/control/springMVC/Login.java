package com.control.springMVC;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.model.User;
import com.server.LoginService;

//1.spring默认接受。do后缀的请求

@Controller
// 类似Struts的Action
@RequestMapping("/Login")
public class Login {

	@RequestMapping("/test/login1.do")
	public ModelAndView testLogin1(User user) {
		// 同样支持参数为表单对象，类似于Struts的ActionForm，User不需要任何配置，直接写即可
		String username = user.getUsername();
		String password = user.getPassword();
		int age = user.getAge();

		if ("admin".equals(username) && "admin".equals(password)) {
			return new ModelAndView("loginSuccess");
		} else {
			return new ModelAndView("loginError");
		}

	}

	@RequestMapping("/test/login2")
	public ModelAndView testLogin2(String username, String password, int age) {
		// request和response不必非要出现在方法中，如果用不上的话可以去掉
		// 参数的名称是与页面控件的name相匹配，参数类型会自动被转换

		if (!"admin".equals(username) || !"admin".equals(password) || age < 5) {
			return new ModelAndView("loginError"); // 手动实例化ModelAndView完成跳转页面（转发），效果等同于上面的方法返回字符串
		}
		return new ModelAndView(new RedirectView("../index.jsp")); // 采用重定向方式跳转页面
		// 重定向还有一种简单写法
		// return new ModelAndView("redirect:../index.jsp");
	}
	
	@RequestMapping("/test/login3")
	public String testLogin5(User user) {
		if (loginService.login(user) == false) {
			return "loginError";
		}
		return "loginSuccess";
	}

	private String getResponse(){
		
		return "";
	}
	

	// @Autowired
	@Resource(name = "loginService")
	// 获取applicationContext.xml中bean的id为loginService的，并注入
	private LoginService loginService; // 等价于spring传统注入方式写get和set方法，这样的好处是简洁工整，省去了不必要得代码


}