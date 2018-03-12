package com.java.concurrency.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class RacingSportCyclicBarrier {
	/*
	 * use of cyclic barrier - similar to count down latch but can be reused to bring all threads to
	 * common sync point in processing
	 */

	public static void main(String[] args) {
		int totalRacer = 3;
		CyclicBarrier c = new CyclicBarrier(totalRacer);
		for(int i=0; i< totalRacer; i++){
			new Thread(new Racer(i, c)).start();
		}
		System.out.println("Race completed in main");
	}

}

class Racer implements Runnable{
	private int racerId;
	private CyclicBarrier c;
	public Racer(int racerId, CyclicBarrier c){
		this.racerId = racerId;
		this.c = c;
	}
	@Override
	public void run(){
		System.out.println("Racer "+racerId+" started session 1");
		try {
			Thread.sleep(3000*racerId);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Racer "+racerId+" completed session 1");
		try {
			c.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Racer "+racerId+" started session 2");
		try {
			Thread.sleep(3000*racerId);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Racer "+racerId+" completed session 2");
		try {
			c.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Race ended");
	}
}
