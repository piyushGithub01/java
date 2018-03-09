package com.java8.consumer.example;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import com.java8.function.example.Employee;

public class ConsumerExample {

	public static void main(String[] args) {
		List<Employee> employeeList = Arrays.asList(new Employee("Tom", 29),
				new Employee("Harry", 25),
				new Employee("Ethan", 35),
				new Employee("Nancy", 40),
				new Employee("Dick", 21));
		
		//defining consumer
		Consumer<Employee> empPrinter = (Employee e) -> System.out.println(e.toString());
		printEmployeeDetails(employeeList, empPrinter);
		
		//consumer chaining
		Consumer<Employee> empDetailsPrinter = empPrinter.andThen(new AgeAlertConsumer());
		printEmployeeDetails(employeeList, empDetailsPrinter);
	}
	
	public static void printEmployeeDetails(List<Employee> empList, Consumer<Employee> employeeConsumer) {
		empList.forEach(employeeConsumer);
	}
}
