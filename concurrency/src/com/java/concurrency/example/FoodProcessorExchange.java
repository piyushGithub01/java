 package com.java.concurrency.example;

import java.util.concurrent.Exchanger;

public class FoodProcessorExchange {
	
	/*
	 * use of Exchanger - to exchange content
	 */

	public static void main(String[] args) {
			Exchanger<String> ex = new Exchanger<>();
			Thread t1 = new Thread(new FoodProcessor("Burger", ex));
			Thread t2 = new Thread(new FoodProcessor("SoftDrink", ex));
			t1.setName("chef one");
			t2.setName("chef two");
			t1.start();
			t2.start();
	}

}

class FoodProcessor implements Runnable{
	private String foodItem;
	private Exchanger<String> ex;
	public FoodProcessor(String foodItem, Exchanger<String> ex){
		this.foodItem = foodItem;
		this.ex = ex;
	}
	@Override 
	public void run(){
		System.out.println(Thread.currentThread().getName() + " Processing " +foodItem);
		try {
			foodItem = ex.exchange(foodItem);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " Processing after exchange " +foodItem);
	}
}
