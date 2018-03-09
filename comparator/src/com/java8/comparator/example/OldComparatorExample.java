package com.java8.comparator.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OldComparatorExample {
	
	public static void main(String[] args) {
		List<Employee> employeeList = Arrays.asList(new Employee("Tom", 29),
				new Employee("Harry", 25),
				new Employee("Ethan", 35),
				new Employee("Nancy", 40),
				new Employee("", 15),
//				new Employee(null, 20),    causes null pointer exception
				new Employee("Dick", 21));
		
		//old comparator
		Collections.sort(employeeList, new OldEmployeeComparator());
		employeeList.forEach(System.out::println);
	}

}
