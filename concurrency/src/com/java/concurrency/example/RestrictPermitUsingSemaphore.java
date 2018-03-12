package com.java.concurrency.example;

import java.util.concurrent.Semaphore;

public class RestrictPermitUsingSemaphore {
	/*
	 * use of semaphore to restrict use of resource to limited number of threads at a time
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserResource u = new UserResource();
		Semaphore s = new Semaphore(2);
		new Thread(new User1(s, u)).start();
		System.out.println("1 executed");
		new Thread(new User2(s, u)).start();
		System.out.println("2 executed");
		new Thread(new User4(s, u)).start();
		System.out.println("3 executed");
		new Thread(new User5(s, u)).start();
		System.out.println("4 executed");
		new Thread(new User3(s, u)).start();
		System.out.println("5 executed");

	}

}

class User1 implements Runnable{
	private Semaphore s;
	private UserResource u;
	public User1(Semaphore s, UserResource u){
		this.s = s;
		this.u = u;
	}

	@Override
	public void run() {
		try {
			s.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("User1 "+u.printAccessCode());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.release();
	}
	
}

class User2 implements Runnable{
	private Semaphore s;
	private UserResource u;
	public User2(Semaphore s, UserResource u){
		this.s = s;
		this.u = u;
	}

	@Override
	public void run() {
		try {
			s.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("User2 "+u.printAccessCode());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.release();
	}
	
}

class User3 implements Runnable{
	private Semaphore s;
	private UserResource u;
	public User3(Semaphore s, UserResource u){
		this.s = s;
		this.u = u;
	}

	@Override
	public void run() {
		try {
			s.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("User3 "+u.printAccessCode());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.release();
	}
	
}

class User4 implements Runnable{
	private Semaphore s;
	private UserResource u;
	public User4(Semaphore s, UserResource u){
		this.s = s;
		this.u = u;
	}

	@Override
	public void run() {
		try {
			s.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("User4 "+u.printAccessCode());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.release();
	}
	
}

class User5 implements Runnable{
	private Semaphore s;
	private UserResource u;
	public User5(Semaphore s, UserResource u){
		this.s = s;
		this.u = u;
	}

	@Override
	public void run() {
		try {
			s.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("User5 "+u.printAccessCode());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.release();
	}
	
}

class UserResource{
 public String printAccessCode(){
	 return "Access Code";
 }
}