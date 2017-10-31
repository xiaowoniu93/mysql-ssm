package com.xszheng.util;

import org.springframework.util.DigestUtils;

public class EncryptUtil {
	
	// md5 盐值字符串，用于md5 混淆
	private static final String salt = "fsdfdsASASQ989&*&))+-";
	
	/**
	 * md5 加密
	 * @descript 
	 * @author xszheng 2017年10月31日下午12:04:05
	 * @param id
	 * @return
	 */
	public static String getMD5(long id){
		String base = id + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

}
