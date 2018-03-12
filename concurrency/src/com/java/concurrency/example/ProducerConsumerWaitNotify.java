package com.java.concurrency.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerWaitNotify {
	/*
	 * producer consumer using wait and notify 
	 */

	public static void main(String[] args) {
		List<Integer> shared = new ArrayList<>();
		int maxSize = 5;
		ProducerWaitNotify p = new ProducerWaitNotify(shared, maxSize);
		ConsumerWaitNotify c = new ConsumerWaitNotify(shared, maxSize);
		ExecutorService e = Executors.newFixedThreadPool(2);
		e.execute(p);
		e.execute(c);
		e.shutdown();

	}

}

class ProducerWaitNotify implements Runnable{
	private List<Integer> shared;
	private int maxSize;
	public ProducerWaitNotify(List<Integer> shared, int maxSize){
		this.shared = shared;
		this.maxSize = maxSize;
	}
	@Override
	public void run() {
		while(true){
			synchronized(shared){
				if(shared.size()==maxSize){
					System.out.println("producer shared queue is full, going to wait for consumer");
					try {
						shared.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("producer adding");
					shared.add(new Random().nextInt(20));
					shared.notifyAll();
				}
			}
		}
	}
	
}


class ConsumerWaitNotify implements Runnable{
	private List<Integer> shared;
	private int maxSize;
	public ConsumerWaitNotify(List<Integer> shared, int maxSize){
		this.shared = shared;
		this.maxSize = maxSize;
	}
	@Override
	public void run() {
		while(true){
			synchronized(shared){
				if(shared.isEmpty()){
					System.out.println("Consumer waiting as shared is empty");
					try {
						shared.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Consumer consumes "+shared.remove(0));
					shared.notifyAll();
				}
			}
		}
	}
	
}
