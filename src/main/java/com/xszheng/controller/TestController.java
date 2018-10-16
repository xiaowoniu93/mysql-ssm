package com.xszheng.controller;

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
	
	/**
	 * 接收入参测试
	 * @param code
	 * @param codes
	 * @return
	 */
	@RequestMapping(value="/receiveParam", method=RequestMethod.GET)
	public String receiveParam(@RequestParam(value="code") Integer code, @RequestParam(value="codes") Integer[] codes){
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
	public JSON aspectTest(@RequestParam(value="name") String name){
		JSONObject returnJson = new JSONObject();
		returnJson.put("name", name);
		return returnJson;
	}
}
