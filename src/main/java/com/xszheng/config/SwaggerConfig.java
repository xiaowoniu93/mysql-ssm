package com.xszheng.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;

@EnableSwagger
public class SwaggerConfig {
	
	private SpringSwaggerConfig swaggerConfig;
	
	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig swaggerConfig){
		this.swaggerConfig = swaggerConfig;
	}
	
	@Bean
	public SwaggerSpringMvcPlugin customImplementation(){
		return new SwaggerSpringMvcPlugin(this.swaggerConfig).apiInfo(apiInfo()).includePatterns(".*seckill.*");
	}
	
	private ApiInfo apiInfo(){
		ApiInfo apiInfo = new ApiInfo("API文档", "mysql-ssm项目集成swagger，第一个api文档", "My Apps API terms of service", "xszheng", "xszheng web app", "licenseUrl");
		return apiInfo;
	}

}
