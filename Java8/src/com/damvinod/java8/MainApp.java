package com.damvinod.java8;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp 
{
	public static void main(String[] args) 
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		HelloWorld obj1 = (HelloWorld) context.getBean("helloWorld1");
		
		//obj1.getMessage();
		
		System.out.println("Initial Name " + obj1.getPerson().getName());
		
		obj1.getPerson().setName("Vinod Reddy");
		
		System.out.println("Modified Name " + obj1.getPerson().getName());
		
		HelloWorld obj2 = (HelloWorld) context.getBean("helloWorld1");
		
		System.out.println("Modified Name from Obj 1 " + obj1.getPerson().getName());
		System.out.println("Modified Name from Obj 2 " + obj2.getPerson().getName());
	}
}