package com.xszheng.service;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xszheng.domain.SkProductStock;
import com.xszheng.vo.Exposer;
import com.xszheng.vo.SeckillExecution;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:spring-mybatis.xml"})
public class SkProductStockServiceTest {
	
	private final Logger log = LoggerFactory.getLogger(SkProductStockServiceTest.class);
	
	@Autowired
	private SkProductStockService skProductStockService;

	@Test
	public void testGetAll() {
		List<SkProductStock> list = skProductStockService.getAll();
		log.info("skproductstock list: {}", list);
	}

	@Test
	public void testGetOneById() {
		SkProductStock productStock = skProductStockService.getOneById(1000);
		log.info("one productstock:{}", productStock);
	}

	@Test
	public void testExportSeckillUrl() {
		Exposer exposer = skProductStockService.exportSeckillUrl(1000);
		log.info("exposer:{}",exposer);
	}

	@Test
	public void testExecuteSeckill() {
		long id = 1000;
		String userPhone = "13787878787";
		String md5 = "2d74e55aaf1643269730dc534a6387ee";
		SeckillExecution skResult = skProductStockService.executeSeckill(id, userPhone, md5);
		log.info("skResult:{}", skResult);
	}

}
