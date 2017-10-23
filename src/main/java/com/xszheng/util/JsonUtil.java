package com.xszheng.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	
	private JSONObject retJson;
	
	public static JsonUtil newJson(){
		JsonUtil json = new JsonUtil();
		json.retJson = new JSONObject();
		json.retJson.put("code", 200);
		json.retJson.put("message", "操作成功");
		return json;
	}
	
	public JsonUtil addData(String key, Object value){
		this.retJson.put(key, value);
		return this;
	}
	
	public JsonUtil addCode(int code){
		this.retJson.put("code", code);
		return this;
	}
	
	public JsonUtil addMessage(String msg){
		this.retJson.put("message", msg);
		return this;
	}
	
	public JSON toJson(){
		return this.retJson;
	}

}
