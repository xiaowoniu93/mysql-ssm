package com.xszheng.vo;

import com.xszheng.domain.SkProductStock;
import com.xszheng.domain.SkSuccessLog;

/**
 * 秒杀成功记录
 * @author xszheng
 *
 */
public class SkSuccessLogVO extends SkSuccessLog {

	/**
	 * 秒杀成功的商品信息
	 */
	private SkProductStock productStock;

	public SkProductStock getProductStock() {
		return productStock;
	}

	public void setProductStock(SkProductStock productStock) {
		this.productStock = productStock;
	}

	@Override
	public String toString() {
		return "SkSuccessLogVO [productStock=" + productStock + "]";
	}

}
