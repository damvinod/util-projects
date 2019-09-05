package com.damvinod.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;

public class Java8 implements MyFirstInterface, MySecondInterface {

	@Override
	public void myMethod() {
		// TODO Auto-generated method stub
		MyFirstInterface.super.myMethod();
	}
	
	public static void main(String args[]){
	
		MyFirstInterface myFirstInterface = new Java8 ();
		MyFirstInterface mySecondInterface = new Java8 ();
		myFirstInterface.myMethod();
		mySecondInterface.myMethod();
		
		List<Person> list = new ArrayList<Person>();
		Map<String, Person> map = new LinkedHashMap<String, Person>();
		
		for(int i=0;i<=10;i++){
			Person person = new Person();
			
			person.setAge(i);
			person.setName("Name " + i);
			
			list.add(person);
			map.put(String.valueOf(person.getAge()), person);
		}
		
		//############ FUNCTIONAL INTERFACE #############  
		
		// Input is Person and Output is void
		Consumer<Person> myConsumer = (Person person) -> {
			System.out.println("Consumer name: " + person.getName());
		};
		myConsumer.accept(list.get(0));
		
		// No Input and Output is Date
		Supplier<Date> mySupplier = () -> {
			return new Date();
		};
		System.out.println("Supplier Time: " + mySupplier.get().getTime());
		
		// Input is Person and Output is boolean
		Predicate<Person> myPredicate = (Person person) -> {
			if(person.getAge() > 5)
				return true;
			else 
				return false;
		};
		System.out.println("Predicate age: " + myPredicate.test(list.get(0)));

		//Input is Person and Output is String
		Function<Person, String> myFunction = (Person s) -> {
			return s.getName();
		};
		System.out.println("Function name: " + myFunction.apply(list.get(0)));

		//############ STREAM LIST #############
		
		Stream<Person> myListStream = list.stream();
		
		System.out.println(myListStream.filter(myPredicate).collect(Collectors.toList()).size());
		
		myListStream.forEach(myConsumer);
		
		Comparator<Person> myComparator = (Person p1, Person p2) -> {
			return p2.getAge().compareTo(p1.getAge());
		};
		
		myListStream.sorted(myComparator).collect(Collectors.toList()).forEach((Person person) -> System.out.println(person.getAge()));
		
		//############ STREAM MAP #############
		
		Stream<Entry<String, Person>> myMapStream = map.entrySet().stream();
		
		Predicate<Entry<String, Person>> mapPredicate = (Entry<String,Person> entry) -> {
			
			if(entry.getValue().getAge() > 5)
				return true;
			else 
				return false;
		};

		Function<Entry<String,Person>, String> myMapKeyFunction = (Entry<String,Person> mapTemp) -> {
			return mapTemp.getKey();
		};
		
		Map<String, Person> filteredMap = myMapStream.filter(mapPredicate).collect(Collectors.toMap(myMapKeyFunction, (Entry<String, Person> entry) -> entry.getValue()));
		
		System.out.println(filteredMap.size());
		System.out.println(map.size());
		
		BiFunction<Person, Integer, Boolean> myBiPredicate = (Person person, Integer age) -> {
			if(person.getAge() > age)
				return true;
			else
				return false;
		};
	}
}
