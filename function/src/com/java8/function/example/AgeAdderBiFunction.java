package com.java8.function.example;

import java.util.function.BiFunction;

public class AgeAdderBiFunction implements BiFunction<Employee, Employee, Integer>{

	@Override
	public Integer apply(Employee emp1, Employee emp2) {
		return emp1.getAge() + emp2.getAge();
	}

}
