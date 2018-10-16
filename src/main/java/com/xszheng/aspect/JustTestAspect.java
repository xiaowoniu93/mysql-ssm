package com.xszheng.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Component
@Aspect
public class JustTestAspect {
	
	@Before("@annotation(com.xszheng.aspect.JustTest)")
	public void doBefore(){
		System.out.println("方法执行前。。。");
	}
	
	@Around("@annotation(com.xszheng.aspect.JustTest)")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("方法名："+pjp.getSignature().getName());
		Object result = pjp.proceed();
		((JSONObject)result).put("aspect", "aspect");
		System.out.println("结果:"+JSON.toJSONString(result));
		return result;
	}
}
