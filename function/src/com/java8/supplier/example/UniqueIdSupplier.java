package com.java8.supplier.example;

import java.util.UUID;
import java.util.function.Supplier;

public class UniqueIdSupplier implements Supplier<String>{

	@Override
	public String get() {
		return UUID.randomUUID().toString();
	}

}
