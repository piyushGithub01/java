package com.java8.predicate.example;

import java.util.function.Predicate;

//implements Predicate FunctionalInterface
public class EvenNumberPredicate implements Predicate<Integer>{

	@Override
	public boolean test(Integer t) {
		return t % 2 == 0;
	}

}
