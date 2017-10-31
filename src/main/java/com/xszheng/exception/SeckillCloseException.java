package com.xszheng.exception;

/**
 * 秒杀结束异常
 * @author xszheng
 *
 */
public class SeckillCloseException extends SeckillException{

	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillCloseException(String message) {
		super(message);
	}

}
