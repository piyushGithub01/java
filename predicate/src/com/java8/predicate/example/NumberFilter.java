package com.java8.predicate.example;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NumberFilter {

	public static void main(String[] args) {
		
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,
				11,12,13,14,15,16,17,18,19,20,21);
		
		//in-line predicate defined
		Predicate<Integer> divisibleBy5 = p -> p % 5 == 0 ;
		
		System.out.println("Even Numbers: ");
		//using predicate class
		numbers.stream().filter(new EvenNumberPredicate()).forEach(n -> {System.out.print(n + " ");});
		System.out.println("");
		
		System.out.println("Odd Numbers: ");
		//predicate NEGATE
		numbers.stream().filter(new EvenNumberPredicate().negate()).forEach(n -> {System.out.print(n + " ");});
		System.out.println("");
		
		System.out.println("Smaller than 3 Numbers: ");
		//passing predefined predicate to filter
		numbers.stream().filter(smallerThan3()).forEach(n -> {System.out.print(n + " ");});
		System.out.println("");
		
		System.out.println("Divisible by 5 Numbers: ");
		numbers.stream().filter(divisibleBy5).forEach(n -> {System.out.print(n + " ");});
		System.out.println("");
		
		System.out.println("Smaller than 3 OR Greater than 18 Numbers: ");
		//passing predicate to function
		filterNumber(numbers, smallerThan3().or(greaterThan18())).forEach(n -> {System.out.print(n + " ");});
		System.out.println("");
		
		//predicate chaining
		Predicate<Integer> greaterThan18AndDivisibleBy5 = greaterThan18().and(divisibleBy5);
		System.out.println("Greater than 18 AND Divisible by 5 Numbers: ");
		//passing predicate to function
		filterNumber(numbers, greaterThan18AndDivisibleBy5).forEach(n -> {System.out.print(n + " ");});
		System.out.println("");

	}
	
	//function accepting Predicate
	public static List<Integer> filterNumber(List<Integer> numbers, Predicate<Integer> criteria) {
		return numbers.stream().filter(criteria).collect(Collectors.toList());
	}
	
	//define predicate using lambda
	public static Predicate<Integer> smallerThan3() {
		return n -> n < 3;
	}
	
	public static Predicate<Integer> greaterThan18() {
		return n -> n > 18;
	}

}
