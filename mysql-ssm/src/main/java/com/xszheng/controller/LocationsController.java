package com.xszheng.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xszheng.domain.Locations;
import com.xszheng.service.LocationsService;

@RestController
@RequestMapping(value="/loc")
public class LocationsController {
	
	private static final Logger log = Logger.getLogger(LocationsController.class);

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
	public String add(@RequestBody Locations loc) throws Exception{
		log.info("进入 add() 方法....");
		locationsService.add(loc);
		return "写入成功";
	}
}
