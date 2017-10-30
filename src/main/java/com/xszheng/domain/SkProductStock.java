package com.xszheng.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="sk_product_stock")
public class SkProductStock {
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 商品名称
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * 库存
	 */
	@Column(name="number")
	private Integer number;
	
	/**
	 * 秒杀开始时间
	 */
	@Column(name="start_time")
	private Date startTime;
	
	/**
	 * 秒杀结束时间
	 */
	@Column(name="end_time")
	private Date endTime;
	
	/**
	 * 创建时间
	 */
	@Column(name="add_time")
	private Date addTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	@Override
	public String toString() {
		return "SkProductStock [id=" + id + ", name=" + name + ", number="
				+ number + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", addTime=" + addTime + "]";
	}

}
