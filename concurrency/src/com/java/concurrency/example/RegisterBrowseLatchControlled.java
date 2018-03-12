package com.java.concurrency.example;

import java.util.concurrent.CountDownLatch;

public class RegisterBrowseLatchControlled {
	/*
	 * use of CountDownLatch -  start processing of send thread only after first thread has completed
	 */

	public static void main(String[] args) {
		CountDownLatch registerTime = new CountDownLatch(1);
		new Thread(new RegistrationProcess(registerTime)).start();
		new Thread(new BrowseVideos(registerTime)).start();
	}

}


class RegistrationProcess implements Runnable{
	private CountDownLatch registerTime;
	public RegistrationProcess(CountDownLatch registerTime){
		this.registerTime = registerTime;
	}
	@Override
	public void run(){
		System.out.println("Registration started for user");
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Registration Completed for user");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		registerTime.countDown();
	}
}

class BrowseVideos implements Runnable{
	private CountDownLatch registerTime;
	public BrowseVideos(CountDownLatch registerTime){
		this.registerTime = registerTime;
	}
	@Override
	public void run(){
		System.out.println("waiting for registration to complete");
		try {
			registerTime.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("now user can browse videos");
	}
}