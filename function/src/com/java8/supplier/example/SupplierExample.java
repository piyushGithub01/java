package com.java8.supplier.example;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class SupplierExample {
	
	public static void main(String[] args) {
		
		System.out.println(" Unique Id : " + new UniqueIdSupplier().get());
		
		Supplier<LocalDateTime> datetimeSupplier = () -> LocalDateTime.now();
		
		System.out.println(" Local Date Time : " + datetimeSupplier.get());
		
	}

}
