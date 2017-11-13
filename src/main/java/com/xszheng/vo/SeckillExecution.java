package com.xszheng.vo;

import com.xszheng.domain.SkSuccessLog;
import com.xszheng.util.SeckillEnum;

/**
 * 秒杀执行结果
 * @author xszheng
 *
 */
public class SeckillExecution {

	/**
	 * 秒杀商品ID
	 */
	private long id;
	
	/**
	 * 秒杀结果状态
	 */
	private int state;
	
	/**
	 * 秒杀结果说明
	 */
	private String stateInfo;
	
	/**
	 * 秒杀成功记录
	 */
	private SkSuccessLog successLog;

	public SeckillExecution(long id, SeckillEnum skEnum, SkSuccessLog successLog) {
		super();
		this.id = id;
		this.state = skEnum.getCode();
		this.stateInfo = skEnum.getMessage();
		this.successLog = successLog;
	}

	public SeckillExecution(long id, SeckillEnum skEnum) {
		super();
		this.id = id;
		this.state = skEnum.getCode();
		this.stateInfo = skEnum.getMessage();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SkSuccessLog getSuccessLog() {
		return successLog;
	}

	public void setSuccessLog(SkSuccessLog successLog) {
		this.successLog = successLog;
	}

	@Override
	public String toString() {
		return "SeckillExecution [id=" + id + ", state=" + state + ", stateInfo=" + stateInfo + ", successLog="
				+ successLog + "]";
	}
	
}
