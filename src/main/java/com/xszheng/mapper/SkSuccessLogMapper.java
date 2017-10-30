package com.xszheng.mapper;

import org.apache.ibatis.annotations.Param;

import com.xszheng.vo.SkSuccessLogVO;

public interface SkSuccessLogMapper {
	
	/**
	 * 插入单条秒杀成功记录
	 * @descript 
	 * @author xszheng 2017年10月23日下午5:43:30
	 * @param proStockId 商品库存表id
	 * @param userPhone 用户手机号
	 * @return
	 */
	int insert(@Param("proStockId") Long proStockId, @Param("userPhone") String userPhone);
	
	/**
	 * 根据ID获取单条记录(注：需同时查出商品信息)
	 * @descript 
	 * @author xszheng 2017年10月23日下午5:45:12
	 * @param proStockId
	 * @return
	 */
	SkSuccessLogVO getOneById(@Param("proStockId") Long proStockId, @Param("userPhone") String userPhone);

}
