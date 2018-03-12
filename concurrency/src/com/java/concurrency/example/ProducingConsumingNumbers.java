package com.java.concurrency.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducingConsumingNumbers {
	/*
	 * use of ArrayBlockingQueue - producer consumer
	 */

	public static void main(String[] args) {
		BlockingQueue<Integer> a = new ArrayBlockingQueue<>(5);
		ProducingNumber p = new ProducingNumber(a);
		ConsumingNumber c = new ConsumingNumber(a);
		ExecutorService e = Executors.newFixedThreadPool(2);
		e.execute(p);
		e.execute(c);
		e.shutdown();
	}

}

class ProducingNumber implements Runnable{
	private BlockingQueue<Integer> a;
	public ProducingNumber(BlockingQueue<Integer> a){
		this.a = a;
	}
	@Override
	public void run(){
		System.out.println("starting producer");
		for(int i=0; i<10; i++){
			try {
				System.out.println("trying to put: "+i);
				a.put(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i==8){
				try {
					System.out.println("going tp sleep at 8");
					Thread.sleep(7000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

class ConsumingNumber implements Runnable{
	BlockingQueue<Integer> a;
	public ConsumingNumber(BlockingQueue<Integer> a){
		this.a = a;
	}
	@Override
	public void run(){
		System.out.println("starting consumer");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0; i<10; i++){
			try {
				System.out.println("consuming : "+a.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
