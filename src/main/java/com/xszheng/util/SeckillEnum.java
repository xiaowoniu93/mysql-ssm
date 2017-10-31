package com.xszheng.util;

public enum SeckillEnum {
	
	SUCCESS(1, "秒杀成功"),
	REPEATKILL(-1, "重复秒杀"),
	CLOSEKILL(0, "秒杀结束"),
	INNER_ERROR(-2, "系统异常"),
	DATA_REWRITE(-3, "数据篡改");
	
	// 状态 code
	private int code;
	
	// 状态 message
	private String message;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	private SeckillEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public static SeckillEnum valueOf(int code){
		for(SeckillEnum skEnum : values()){
			if(skEnum.code == code){
				return skEnum;
			}
		}
		return null;
	}
	
}
