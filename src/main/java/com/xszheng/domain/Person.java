package com.xszheng.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class Person extends ApplicationContextEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5994301405901936869L;

	public Person(ApplicationContext source) {
		super(source);
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
}
