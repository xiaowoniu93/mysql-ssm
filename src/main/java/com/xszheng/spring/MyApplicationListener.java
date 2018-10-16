package com.xszheng.spring;

import org.springframework.context.ApplicationListener;

import com.xszheng.domain.Person;

public class MyApplicationListener implements ApplicationListener<Person> {

	@Override
	public void onApplicationEvent(Person arg0) {
		System.err.println("listener..........");
	}

}
