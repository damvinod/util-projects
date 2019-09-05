package com.damvinod.spring.basics;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAppAnnotations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfig.class);

		HelloWorld obj1 = (HelloWorld) ctx.getBean("helloworld1");
		obj1.setMessage("Hello World!");
		obj1.getRuleBookMap();
		
		HelloWorld obj2 = (HelloWorld) ctx.getBean("helloworld2");
		
		System.out.println(obj1.ruleBookMap.size());
		System.out.println(obj2.ruleBookMap.size());

	}

}
