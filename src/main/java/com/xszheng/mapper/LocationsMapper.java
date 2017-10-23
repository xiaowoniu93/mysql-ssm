package com.xszheng.mapper;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.xszheng.domain.Locations;

//@MapperScan
public interface LocationsMapper extends Mapper<Locations>{
	
	// 写入单条记录
	public int add(Locations loc);
	
	// 无条件获取所有记录
	public List<Locations> getAll();
}
