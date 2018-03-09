package com.java8.function.example;

import java.util.function.Function;

public class NameToLowerCaseFunction implements Function<String, String>{

	@Override
	public String apply(String t) {
		return t.toLowerCase();
	}

}
