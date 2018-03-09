package com.java8.comparator.example;

import java.util.Comparator;

//old way of defining comparator
public class OldEmployeeComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
