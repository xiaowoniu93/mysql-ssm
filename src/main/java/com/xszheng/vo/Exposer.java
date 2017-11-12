package com.xszheng.vo;

/**
 * 暴露秒杀信息
 * @author xszheng
 *
 */
public class Exposer {
	
	/**
	 * 是否开启秒杀
	 */
	private boolean exposed;

	/**
	 * md5 加密
	 */
	private String md5;
	
	/**
	 * 秒杀商品ID
	 */
	private long id;
	
	/**
	 * 系统当前时间(毫秒)
	 */
	private long nowTime;
	
	/**
	 * 秒杀起始时间
	 */
	private long startTime;
	
	/**
	 * 秒杀结束时间
	 */
	private long endTime;

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNowTime() {
		return nowTime;
	}

	public void setNowTime(long nowTime) {
		this.nowTime = nowTime;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public Exposer(boolean exposed, String md5, long id) {
		super();
		this.exposed = exposed;
		this.md5 = md5;
		this.id = id;
	}

	public Exposer(boolean exposed, long id, long nowTime, long startTime, long endTime) {
		super();
		this.exposed = exposed;
		this.id = id;
		this.nowTime = nowTime;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Exposer(boolean exposed, long id) {
		super();
		this.exposed = exposed;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Exposer [exposed=" + exposed + ", md5=" + md5 + ", id=" + id + ", nowTime=" + nowTime + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}
	
}
