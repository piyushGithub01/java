package com.java.concurrency.example;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicCheckBooleanSimple {

	/*
	 * try operation on atomic boolean - compare and set
	 * 
	 * can be used in multi thread environment to run any activity only once when initial value was true.
	 * once activity is run, change value of boolean variable to false
	 */
	public static void main(String[] args) {
		CheckBoolean c = new CheckBoolean();
		c.tryBoolean(c.b);
	}

}

class CheckBoolean{
	AtomicBoolean b = new AtomicBoolean(false);
	public void tryBoolean(AtomicBoolean b){
		System.out.println("initial boolean value : "+b);
		b.compareAndSet(true, true);
		System.out.println("after comapre on true and set operation : "+b);
		b.compareAndSet(false, true);
		System.out.println("after compare on false and set operation : "+b);
	}
}
