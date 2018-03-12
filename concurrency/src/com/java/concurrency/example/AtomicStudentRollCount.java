package com.java.concurrency.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicStudentRollCount {
	/*
	 * use of atomic integer or any atomic variable
	 * 
	 * use in multi thread environment, many thread can access and modify value of atomic variable without 
	 * race around condition and without use of synchronized or any locks
	 * atomic looks like most granular lock in itself
	 */

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch c = new CountDownLatch(10);
		RollCallRegister r = new RollCallRegister();
		int waitTime = 1000;
		for(int a = 0; a < 10; a++){
			Thread t = new Thread(new StudentThread(a,r,c,waitTime*a));
			t.start();
		}
		c.await();
		System.out.println("Total Number of student who regiterd are : "+r.getStudentCount());
	}

}

class StudentThread implements Runnable{
	private int rollNumber;
	private RollCallRegister register;
	private CountDownLatch c;
	private int waitTime;
	public StudentThread(int rollNumber, RollCallRegister register, CountDownLatch c, int waitTime){
		this.register = register;
		this.rollNumber = rollNumber;
		this.c = c;
		this.waitTime = waitTime;
	}
	@Override
	public void run(){
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("student : "+rollNumber+" so far registered "+register.i.incrementAndGet());
		c.countDown();
	}
}

class RollCallRegister{
	AtomicInteger i = new AtomicInteger(0);
	
	public Integer getStudentCount(){
		return i.get();
	}
}
