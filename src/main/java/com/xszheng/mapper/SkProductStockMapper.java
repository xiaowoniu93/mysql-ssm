package com.xszheng.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xszheng.domain.SkProductStock;

public interface SkProductStockMapper {
	
	/**
	 * 减库存
	 * @descript 
	 * @author xszheng 2017年10月23日下午5:34:09
	 * @param id 商品id
	 * @param killTime 秒杀时间
	 * @return
	 */
	int reduceStock(@Param("id") Long id, @Param("killTime") Date killTime);
	
	/**
	 * 根据主键获取秒杀商品信息
	 * @descript 
	 * @author xszheng 2017年10月23日下午5:35:15
	 * @param id
	 * @return
	 */
	SkProductStock getOneById(Long id);
	
	/**
	 * 根据偏移量查询指定数量的商品信息
	 * @descript 
	 * @author xszheng 2017年10月23日下午5:37:06
	 * @param skipSize
	 * @param pageSize
	 * @return
	 */
	List<SkProductStock> getListByPage(@Param("skipSize") int skipSize, @Param("pageSize") int pageSize);

	/**
	 * 利用存储过程执行秒杀逻辑(减库存、新增秒杀记录)
	 * @descript 
	 * @author xszheng 2018年3月6日上午10:18:32
	 * @param params
	 */
	void seckillByProcedure(Map<String, Object> params);
}
