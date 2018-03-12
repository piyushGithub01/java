package com.java.concurrency.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BathRoomLock {
	/*
	 * use of Lock to gain exclusive lock on a resource
	 */

	public static void main(String[] args) {
		BathRoom b = new BathRoom();
		ExecutorService e = Executors.newFixedThreadPool(4);
		e.execute(new Swimmer1(b));
		System.out.println("1 executed");
		e.execute(new Thread(new Swimmer2(b)));
		System.out.println("2 executed");
		e.execute(new Thread(new Swimmer3(b)));
		System.out.println("3 executed");
		e.execute(new Thread(new Swimmer4(b)));
		System.out.println("4 executed");
		e.execute(new Thread(new Swimmer5(b)));
		System.out.println("5 executed");
		e.shutdown();
	}

}

class Swimmer1 implements Runnable{
	private BathRoom b;
	public Swimmer1(BathRoom b){
		this.b = b;
	}
	@Override
	public void run() {
		b.l.lock();
		System.out.println("Swimmer1 will be "+b.useBatchRoom());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Swimmer1 will stop "+b.useBatchRoom());
		b.l.unlock();
	}
	
}

class Swimmer2 implements Runnable{
	private BathRoom b;
	public Swimmer2(BathRoom b){
		this.b = b;
	}
	@Override
	public void run() {
		b.l.lock();
		System.out.println("Swimmer2 will be "+b.useBatchRoom());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Swimmer2 will stop "+b.useBatchRoom());
		b.l.unlock();
	}
	
}

class Swimmer3 implements Runnable{
	private BathRoom b;
	public Swimmer3(BathRoom b){
		this.b = b;
	}
	@Override
	public void run() {
		b.l.lock();
		System.out.println("Swimmer3 will be "+b.useBatchRoom());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Swimmer3 will stop "+b.useBatchRoom());
		b.l.unlock();
	}
	
}

class Swimmer4 implements Runnable{
	private BathRoom b;
	public Swimmer4(BathRoom b){
		this.b = b;
	}
	@Override
	public void run() {
		b.l.lock();
		System.out.println("Swimmer4 will be "+b.useBatchRoom());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Swimmer4 will stop "+b.useBatchRoom());
		b.l.unlock();
	}
	
}

class Swimmer5 implements Runnable{
	private BathRoom b;
	public Swimmer5(BathRoom b){
		this.b = b;
	}
	@Override
	public void run() {
		b.l.lock();
		System.out.println("Swimmer5 will be "+b.useBatchRoom());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Swimmer5 will stop "+b.useBatchRoom());
		b.l.unlock();
	}
	
}

class BathRoom{
	Lock l = new ReentrantLock();
	public String useBatchRoom(){
		return "using bathroom";
	}
}
