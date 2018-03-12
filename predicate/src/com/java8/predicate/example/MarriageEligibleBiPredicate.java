package com.java8.predicate.example;

import java.util.function.BiPredicate;

public class MarriageEligibleBiPredicate implements BiPredicate<String, Integer>{

	@Override
	public boolean test(String gender, Integer age) {
		return ("MALE".equalsIgnoreCase(gender) && age > 21) ||
				("FEMALE".equalsIgnoreCase(gender) && age > 18);
	}

}
