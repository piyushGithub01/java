package com.java8.consumer.example;

import java.util.function.Consumer;

import com.java8.function.example.Employee;

public class AgeAlertConsumer implements Consumer<Employee>{

	@Override
	public void accept(Employee t) {
		if (t.getAge() > 25)
			System.out.println("Employee is older than 25 years age!");
		else 
			System.out.println("Employee is under age of 25 years!");
	}

}
