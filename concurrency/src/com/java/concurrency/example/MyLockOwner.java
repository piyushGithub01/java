package com.java.concurrency.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class MyLockOwner {
	
	/*
	 * create your own custom Locks by extending Lock / ReentrantLock Lock class
	 */

	public static void main(String[] args) {
		Room r = new Room();
		Worker1 w1 = new Worker1(r);
		Worker2 w2 = new Worker2(r);
		ExecutorService e = Executors.newFixedThreadPool(2);
		e.execute(new Thread(w1));
		e.execute(new Thread(w2));
		e.shutdown();
		
	}
}

class Worker1 implements Runnable{
	private Room r;
	public Worker1(Room r){
		this.r = r;
	}
	@Override
	public void run(){
		r.ml.lock();
		System.out.println("Worker1 "+r.ml.owner());
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		r.ml.unlock();
	}
}

class Worker2 implements Runnable{
	private Room r;
	public Worker2(Room r){
		this.r = r;
	}
	@Override
	public void run(){
		r.ml.lock();
		System.out.println("Worker2 "+r.ml.owner());
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		r.ml.unlock();
	}
}

class Room{
	MyReentrantLock ml = new MyReentrantLock();
}

class MyReentrantLock extends ReentrantLock{
	String owner(){
		Thread t = this.getOwner();
		if(t != null){
			return t.getName();
		}else{
			return "none";
		}
	}
}
