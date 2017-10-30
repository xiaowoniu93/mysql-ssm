package com.xszheng.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name="sk_success_log")
public class SkSuccessLog {
	
	/**
	 * 商品id
	 */
	@Column(name="pro_stock_id")
	private Long proStockId;
	
	/**
	 * 用户手机号
	 */
	@Column(name="user_phone")
	private String userPhone;
	
	/**
	 * 状态表示：-1：无效 0：成功 1：已付款 2：已发货
	 */
	@Column(name="state")
	private Integer state;
	
	/**
	 * 创建时间
	 */
	@Column(name="add_time")
	private Date addTime;

	public Long getProStockId() {
		return proStockId;
	}

	public void setProStockId(Long proStockId) {
		this.proStockId = proStockId;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Override
	public String toString() {
		return "SkSuccessLog [proStockId=" + proStockId + ", userPhone="
				+ userPhone + ", state=" + state + ", addTime=" + addTime + "]";
	}
	
}
