package com.xszheng.service;

import java.util.List;

import com.xszheng.domain.Locations;

public interface LocationsService {
	
	// 写入单条记录
	public int add(Locations loc);
	
	
	// 无条件获取所有记录
	public List<Locations> getAll() throws Exception;
}
