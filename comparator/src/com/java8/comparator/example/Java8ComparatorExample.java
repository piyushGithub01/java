package com.java8.comparator.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Java8ComparatorExample {
	
	public static void main(String[] args) {
		List<Employee> employeeList = Arrays.asList(new Employee("Tom", 29),
				new Employee("Harry", 25),
				new Employee("Ethan", 35),
				new Employee("Ethan", 34),
				new Employee("Nancy", 40),
				new Employee("", 15),
//				new Employee(null, 20),    causes null pointer exception
				new Employee("Dick", 21));
		
		//define comparator using lambda
		Comparator<Employee> byName = (Employee e1, Employee e2) -> {
			return e1.getName().compareTo(e2.getName());
		};
		
		Collections.sort(employeeList, byName);
		System.out.println("sort by name");
		employeeList.forEach(System.out::println);
		
		//define comparator using static method
		Comparator<Employee> byAge = Comparator.comparing(Employee::getAge);
		Collections.sort(employeeList, byAge);
		System.out.println("sort by age");
		employeeList.forEach(System.out::println);
		
		//multiple criteria using thenComparing
		Comparator<Employee> byNameByAge = Comparator.comparing(Employee::getName)
				.thenComparing(Employee::getAge);
		Collections.sort(employeeList, byNameByAge);
		System.out.println("sort by name and then by age");
		employeeList.forEach(System.out::println);
		
		//natural order sorting
		List<String> empNames = employeeList.stream().map(Employee::getName).collect(Collectors.toList());
		empNames.sort(Comparator.naturalOrder());
		System.out.println("sort name by natural order");
		empNames.forEach(System.out::println);
		
		//reverse comparator
		Comparator<Employee> byNameReverse = Comparator.comparing(Employee::getName).reversed();
		Collections.sort(employeeList, byNameReverse);
		System.out.println("reverse sort by name");
		employeeList.forEach(System.out::println);
		
		//Comparator nullsFirst or nullsLast
		List<Employee> employeeNullNameList = Arrays.asList(new Employee("Tom", 29),
				new Employee("Harry", 25),
				new Employee("Ethan", 35),
				new Employee("Ethan", 34),
				new Employee("Nancy", 40),
				new Employee("", 15),
				new Employee(null, 20),  
				new Employee("Dick", 21));
		Comparator<Employee> byNameNullFirst = Comparator.comparing(Employee::getName, 
				Comparator.nullsFirst(String::compareTo));
		Collections.sort(employeeNullNameList, byNameNullFirst);
		System.out.println("sort by name and null names first");
		employeeNullNameList.forEach(System.out::println);
	}

}
