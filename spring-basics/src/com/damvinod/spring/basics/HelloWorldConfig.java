package com.damvinod.spring.basics;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldConfig {
	
   @Bean(name="helloworld1")
   public HelloWorld helloWorld(){
      return new HelloWorld();
   }
   
   @Bean(name="helloworld2")
   public HelloWorld helloWorld2(){
      return new HelloWorld();
   }
   
}
