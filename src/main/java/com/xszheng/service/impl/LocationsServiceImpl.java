package com.xszheng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xszheng.domain.Locations;
import com.xszheng.mapper.LocationsMapper;
import com.xszheng.service.LocationsService;

@Service
public class LocationsServiceImpl implements LocationsService {
	
	@Autowired
	private LocationsMapper locationsMapper;
	

	@Override
	public int add(Locations loc) {
		return locationsMapper.insert(loc);
	}

}
