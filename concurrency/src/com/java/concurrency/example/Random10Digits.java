package com.java.concurrency.example;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class Random10Digits {
	/*
	 * produce 10 random numbers
	 */

	public static void main(String[] args) {
		Generator g = new Generator(0, 10);
		Thread t = new Thread(g);
		t.start();
	}

}

class Generator implements Runnable{
	int start;
	int end;
	public Generator(int start, int end){
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void run(){
		HashSet<Integer> s = new HashSet<>();
		while(s.size() != 10){
			int local = ThreadLocalRandom.current().nextInt(start, end);
			System.out.println(local);
			s.add(local);
		}
		s.stream().forEach(System.out::print);
	}
}