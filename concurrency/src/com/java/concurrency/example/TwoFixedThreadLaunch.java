package com.java.concurrency.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TwoFixedThreadLaunch {
	/*
	 * use of ExecutorService - to initiate fixed number of threads
	 */

	public static void main(String[] args) {
		Thread t1 = new Thread(new EnglishPrinting());
		Thread t2 = new Thread(new FrenchPrinting());
		Thread t3 = new Thread(new SpanishPrinting());
		Thread t4 = new Thread(new GermanPrinting());
		
		ExecutorService e = Executors.newFixedThreadPool(2);
		e.execute(new EnglishPrinting());
		System.out.println("1 executed");
		e.execute(t2);
		System.out.println("2 executed");
		e.execute(t3);
		System.out.println("3 executed");
		e.execute(t4);
		System.out.println("4 executed");
		e.shutdown();
	}

}

class EnglishPrinting implements Runnable{

	@Override
	public void run() {
		for(int i=0; i<5; i++){
			System.out.println("English");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}


class FrenchPrinting implements Runnable{

	@Override
	public void run() {
		for(int i=0; i<5; i++){
			System.out.println("French");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}


class SpanishPrinting implements Runnable{

	@Override
	public void run() {
		for(int i=0; i<5; i++){
			System.out.println("Spanish");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}


class GermanPrinting implements Runnable{

	@Override
	public void run() {
		for(int i=0; i<5; i++){
			System.out.println("German");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
