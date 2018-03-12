package com.java.concurrency.example;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PriceDiscountCallableFuture {
	/*
	 * use of callable future
	 */

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		PriceCalculator p = new PriceCalculator("101");
		DiscountCalculator d = new DiscountCalculator("101");
		
		ExecutorService e = Executors.newFixedThreadPool(2);
		Future<Double> price = e.submit(p);
		Future<Double> discount = e.submit(d);
		e.shutdown();
		
		System.out.println("price of 101 is : "+price.get()+LocalDateTime.now());
		System.out.println("discount of 101 is : "+discount.get()+LocalDateTime.now());
	}

}

class PriceCalculator implements Callable<Double>{

	private String itemCode;
	
	public PriceCalculator(String itemCode){
		this.itemCode = itemCode;
	}
	
	@Override
	public Double call() throws Exception {
		Double price = Double.valueOf("0.00");
		switch(this.itemCode){
			case "101": price = Double.valueOf("100.00"); break;
			case "102": price = Double.valueOf("50.00"); break;
		}
		Thread.sleep(10000);
		return price;
	}
	
}

class DiscountCalculator implements Callable<Double>{

	private String itemCode;
	
	public DiscountCalculator(String itemCode){
		this.itemCode = itemCode;
	}
	
	@Override
	public Double call() throws Exception {
		Double discount = Double.valueOf("0.00");
		switch(itemCode){
			case "101" : discount = Double.valueOf("10.00"); break;
			case "102" : discount = Double.valueOf("5.00"); break;
		}
		Thread.sleep(1000);
		return discount;
	}
	
}
