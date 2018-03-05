package com.xszheng.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xszheng.domain.SkProductStock;
import com.xszheng.mapper.SkProductStockMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:spring-mybatis.xml"})
public class RedisServiceTest {

	private final long id = 1000;
	
	@Autowired
	private SkProductStockMapper productStockMapper;
	
	@Autowired
	private RedisService rediserService;
	
	@Test
	public void testProduct() {
		SkProductStock product = rediserService.getProduct(id);
		if(product == null){
			product = productStockMapper.getOneById(id);
			if(product != null){
				String result = rediserService.putProduct(product);
				System.out.println(result);
				product = rediserService.getProduct(id);
				System.out.println(product);
			}
		}
	}

}
