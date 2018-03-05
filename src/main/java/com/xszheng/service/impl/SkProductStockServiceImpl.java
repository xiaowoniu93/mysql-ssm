package com.xszheng.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xszheng.cache.RedisService;
import com.xszheng.domain.SkProductStock;
import com.xszheng.exception.RepeatKillException;
import com.xszheng.exception.SeckillCloseException;
import com.xszheng.exception.SeckillException;
import com.xszheng.mapper.SkProductStockMapper;
import com.xszheng.mapper.SkSuccessLogMapper;
import com.xszheng.service.SkProductStockService;
import com.xszheng.util.EncryptUtil;
import com.xszheng.util.SeckillEnum;
import com.xszheng.vo.Exposer;
import com.xszheng.vo.SeckillExecution;
import com.xszheng.vo.SkSuccessLogVO;

@Service
public class SkProductStockServiceImpl implements SkProductStockService {
	
	private final Logger log = LoggerFactory.getLogger(SkProductStockServiceImpl.class);
	
	@Autowired
	private SkProductStockMapper skProductStockMapper;
	
	@Autowired
	private SkSuccessLogMapper skSuccessLogMapper;
	
	@Autowired
	private RedisService redisService;

	@Override
	public List<SkProductStock> getAll() {
		List<SkProductStock> list = skProductStockMapper.getListByPage(0, 10);
		return list;
	}

	@Override
	public SkProductStock getOneById(long id) {
		return skProductStockMapper.getOneById(id);
	}

	// 秒杀开启时输出秒杀接口地址，否则输出系统时间和秒杀开启时间
	@Override
	public Exposer exportSeckillUrl(long id) {
		// 优化点：缓存优化
		SkProductStock productStock = redisService.getProduct(id);
		if(productStock == null){
			productStock = skProductStockMapper.getOneById(id);
			if(productStock == null){
				return new Exposer(false, id);
			}else{
				redisService.putProduct(productStock);
			}
		}
		long nowTime = new Date().getTime();
		long startTime = productStock.getStartTime().getTime();
		long endTime = productStock.getEndTime().getTime();
		if(startTime > nowTime || nowTime > endTime){
			return new Exposer(false, id, nowTime, startTime, endTime);
		}
		return new Exposer(true, EncryptUtil.getMD5(id), id);
	}
	
	@Override
	@Transactional
	public SeckillExecution executeSeckill(long id, String userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
		if(md5 == null || !EncryptUtil.getMD5(id).equals(md5)){
			throw new SeckillException("秒杀信息有误");
		}
		try {
			// 生成秒杀成功的记录
			int logCount = skSuccessLogMapper.insert(id, userPhone);
			if(logCount <= 0){
				throw new RepeatKillException("超出秒杀件数");
			}else{
				// 减库存
				int reduceCount = skProductStockMapper.reduceStock(id, new Date());
				if(reduceCount <= 0){
					throw new SeckillCloseException("秒杀已结束");
				}else{
					SkSuccessLogVO successLogVo = skSuccessLogMapper.getOneById(id, userPhone);
					return new SeckillExecution(id, SeckillEnum.SUCCESS, successLogVo);
				}
			}
			
		} catch (Exception e) {
			log.info("#SkProductStockServiceImpl #executeSeckill has a error:"+e.getMessage());
			throw new SeckillException("秒杀发生异常::"+e.getMessage());
		}
		
	}
	
}
