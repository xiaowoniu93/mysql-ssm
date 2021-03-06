package com.xszheng.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.xszheng.domain.Locations;
import com.xszheng.service.LocationsService;
import com.xszheng.util.JsonUtil;

@RestController
@RequestMapping(value="/loc")
public class LocationsController {
	
	private static final Logger log = LoggerFactory.getLogger(LocationsController.class);

	@Autowired
	private LocationsService locationsService;
	
	/**
	 * 添加
	 * @descript 
	 * @author xszheng 2017年9月19日下午3:22:24
	 * @param loc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(/*@RequestBody*/ Locations loc, HttpServletRequest request) throws Exception{
		System.out.println(request.getParameter("streetAddress"));
		log.info("进入 add() 方法....");
		locationsService.add(loc);
		return "写入成功";
	}
	
	/**
	 * 无条件获取所有记录
	 * @descript 
	 * @author xszheng 2017年10月23日下午3:02:39
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/all")
	public JSON list() throws Exception{
		List<Locations> list = locationsService.getAll();
		return JsonUtil.newJson().addData("data", list).toJson();
	}
	
	/**
	 * 用fastjson 返回对象，测试数值类型的属性为null 时是否会自动转换为0【不会】
	 * @author xszheng
	 * @date 2018年5月7日下午8:06:38
	 * @description
	 * @param
	 */
	@RequestMapping(value="/testResp")
	public JSON testResp() throws Exception{
		Locations loc = new Locations();
		loc.setId(null);
		return JsonUtil.newJson().addData("data", loc).toJson();
	}
}
