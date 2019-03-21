package com.xszheng.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xszheng.aspect.JustTest;

@RestController
@RequestMapping(value="/test")
public class TestController {
	
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	/**
	 * 接收入参测试
	 * @param code
	 * @param codes
	 * @return
	 */
	@RequestMapping(value="/receiveParam", method=RequestMethod.GET)
	public String receiveParam(@RequestParam(value="code") Integer code, @RequestParam(value="codes") Integer[] codes){
		logger.info("code={}, codes={}", code, codes);
		JSONObject param = new JSONObject();
		param.put("code", code);
		param.put("codes", codes);
		return JSON.toJSONString(param);
	}

	/**
	 * 切面测试
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/aspectTest", method={RequestMethod.GET, RequestMethod.POST})
	@JustTest
	public JSON aspectTest(@RequestParam(value="name") String name, HttpServletRequest request){
		System.err.println("request.getParameterMap():"+request.getParameterMap());
		System.err.println("servlet path:"+request.getServletPath());	// /test/aspectTest
		System.err.println("context path:"+request.getContextPath());	// /ssm
		System.err.println("URI:"+request.getRequestURI());		// /ssm/test/aspectTest
		System.err.println("URL:"+request.getRequestURL());		// http://localhost:8080/ssm/test/aspectTest
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("name"));
		JSONObject returnJson = new JSONObject();
		returnJson.put("name", name);
		return returnJson;
	}
}
