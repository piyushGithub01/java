package com.java8.consumer.example;

import java.util.function.BiConsumer;

public class AgePrinterBiConsumer implements BiConsumer<String, Integer>{

	@Override
	public void accept(String gender, Integer age) {
		System.out.println(" gender is : " + gender + "and age is : " + age);
	}

}
