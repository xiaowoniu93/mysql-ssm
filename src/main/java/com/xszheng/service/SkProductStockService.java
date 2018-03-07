package com.xszheng.service;

import java.util.List;

import com.xszheng.domain.SkProductStock;
import com.xszheng.exception.RepeatKillException;
import com.xszheng.exception.SeckillCloseException;
import com.xszheng.exception.SeckillException;
import com.xszheng.vo.Exposer;
import com.xszheng.vo.SeckillExecution;

public interface SkProductStockService {
	
	/**
	 * 查询所有参与秒杀的商品
	 * @descript 
	 * @author xszheng 2017年10月31日上午10:14:55
	 * @return
	 */
	public List<SkProductStock> getAll();
	
	/**
	 * 根据id查询单个商品
	 * @descript 
	 * @author xszheng 2017年10月31日上午10:15:43
	 * @param id
	 * @return
	 */
	public SkProductStock getOneById(long id);
	
	/**
	 * 秒杀开启时输出秒杀接口地址，否则输出系统时间和秒杀开启时间
	 * @descript 
	 * @author xszheng 2017年10月31日上午10:33:05
	 * @param id
	 * @return
	 */
	Exposer exportSeckillUrl(long id);
	
	/**
	 * 执行秒杀
	 * @descript 
	 * @author xszheng 2017年10月31日上午10:36:20
	 * @param id
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckill(long id, String userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException;
	
	/**
	 * 执行秒杀-by procedure
	 * @descript 
	 * @author xszheng 2017年10月31日上午10:36:20
	 * @param id
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckillByProcedure(long id, String userPhone, String md5);
}
