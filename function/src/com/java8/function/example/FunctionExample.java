package com.java8.function.example;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionExample {
	
	public static void main(String args[]) {
		List<Employee> employeeList = Arrays.asList(new Employee("Tom", 29),
				new Employee("Harry", 25),
				new Employee("Ethan", 35),
				new Employee("Nancy", 40),
				new Employee("Dick", 21));
		
		//Defined a function to convert object to another object/string
		Function<Employee, String> employeeToEmpName = (Employee e) -> {return e.getName();};
		
		List<String> empNames = convertEmployeeListToEmpNameList(employeeList, employeeToEmpName);
		System.out.println("Employee object to employee names");
		empNames.forEach(System.out::println);
		System.out.println(" ");
		
		//defined another function
		Function<String, String> empNameTo3CharEmpName = (String s) -> s.substring(0, 3);
		
		//Function chaining
		//function pass to andthen() executes on result of first function
		Function<Employee, String> employeeTo3CharEmpName = employeeToEmpName.andThen(empNameTo3CharEmpName);
		
		List<String> emp3CharNames = convertEmployeeListToEmpNameList(employeeList, employeeTo3CharEmpName);
		System.out.println("Employee object to 3 char employee names");
		emp3CharNames.forEach(System.out::println);
		System.out.println(" ");
		
		//defined another function
		Function<Employee, Employee> employeeToEmployeeUpperCaseName = 
				(Employee e) -> { String name = e.getName(); 
					e.setName(name.toUpperCase());
					return e;};
		
		//Function chaining
		//convert employee object to employee with upper case name object using compose()
		//compose() runs before main function
		//convert upper case employee object to employee name with 3 char list
		//emp name to 3 char name conversion in andthen() which runs after main function
		Function<Employee, String> employeeTo3CharEmpNameUpper = 
				employeeToEmpName.compose(employeeToEmployeeUpperCaseName).andThen(empNameTo3CharEmpName);
		List<String> emp3CharNamesUpper = convertEmployeeListToEmpNameList(employeeList, employeeTo3CharEmpNameUpper);
		System.out.println("Employee object to 3 char employee names in upper case");
		emp3CharNamesUpper.forEach(System.out::println);
		System.out.println(" ");
		
		//Function chaining
		//function pass to andthen() executes on result of first function
		Function<Employee, String> employeeToLowerCaseEmpName = employeeToEmpName.andThen(new NameToLowerCaseFunction());
				
		List<String> empLowercaseNames = convertEmployeeListToEmpNameList(employeeList, employeeToLowerCaseEmpName);
		System.out.println("Employee object to lower case employee names");
		empLowercaseNames.forEach(System.out::println);
		System.out.println(" ");
	}

	public static List<String> convertEmployeeListToEmpNameList(List<Employee> empList,
			Function<Employee, String> employeeToEmpName) {
		//map accepts Function<Employee, ?>
		//is equivalent to employeeToEmpName.apply(singleEmployeeObject);
		return empList.stream().map(employeeToEmpName).collect(Collectors.toList());
	}
}
