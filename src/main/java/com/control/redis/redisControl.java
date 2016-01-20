package com.control.redis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class redisControl {
	@RequestMapping(value="getredis")
	public String readdate(){
		
		return "redis";
	}
}
