package com.java.concurrency.example;

import java.util.concurrent.CountDownLatch;

public class FactoryWorkerOpenOnCount {
	
	/*
	 * use of CountDownLatch - to start the main process when already required number of thread/processes
	 * has already started
	 */

	public static void main(String[] args) throws InterruptedException {
		int totalWorker = 10;
		CountDownLatch startCount = new CountDownLatch(1);
		CountDownLatch endCount = new CountDownLatch(totalWorker);
		for(int i =0; i < totalWorker; i++){
			new Thread(new Worker(startCount, endCount, i)).start();
		}
		startCount.countDown();
		System.out.println("factory is open for workers");
		endCount.await();
		System.out.println("factory closing as workers finished work");
	}

}

class Worker implements Runnable{
	private CountDownLatch startCount;
	private CountDownLatch endCount;
	private int name;
	public Worker(CountDownLatch startCount, CountDownLatch endCount, int name){
		this.startCount = startCount;
		this.endCount = endCount;
		this.name = name;
	}
	@Override
	public void run(){
		System.out.println("Worker "+name+" reached factory, waiting for factory to open");
		try {
			startCount.await();
			Thread.sleep(1000*name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Worker "+name+" completed work");
		endCount.countDown();
	}
}
