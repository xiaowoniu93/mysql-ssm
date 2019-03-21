package com.xszheng.mdc;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

/**
 * mdc
 * @author zhengxiaosun
 *
 */
public class LogTrace {
	
	private static final String TRACEID = "tradeId";
	
	/**
	 * 设置mdc
	 * @return
	 */
	public static boolean enterTrace(){
		String traceId = MDC.get(TRACEID);
		if(StringUtils.isNotBlank(traceId)){
			return false;
		}
		traceId = "tid" + UUID.randomUUID().toString().replaceAll("-", "");
		MDC.put(TRACEID, traceId);
		return true;
	}

	/**
	 * 清除mdc
	 */
	public static void exitTrace(){
		MDC.remove(TRACEID);
	}
	
	/**
	 * 获取mdc
	 * @return
	 */
	public static String getTrace(){
		return MDC.get(TRACEID);
	}
	
}
