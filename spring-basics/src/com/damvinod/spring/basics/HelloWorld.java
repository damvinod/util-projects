package com.damvinod.spring.basics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloWorld {
	
	private String message;
	
	private Person person;
	
	public Map<String, List<String>> ruleBookMap = new HashMap<String, List<String>>();
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void getMessage() {
		System.out.println("Your Message : " + message);
	}
	
	HelloWorld(String message, Person person){
		this.message = message;
		this.person = person;
	}
	
	public HelloWorld() {
		
	}
	
	public Map<String, List<String>> getRuleBookMap() {
		//if (ruleBookMap == null || ruleBookMap.isEmpty()) {
			
			List<String> aList = new ArrayList<String>();
			
			aList.add("2");
			
			ruleBookMap.put("1", aList);
		//}

		return ruleBookMap;
	}
}