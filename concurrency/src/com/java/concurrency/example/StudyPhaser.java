package com.java.concurrency.example;

import java.util.concurrent.Phaser;

public class StudyPhaser {
	/*
	 * use of Phaser - to synch or activate threads/tasks
	 * at run time Phaser can register or unregister tasks from sync point
	 */

	public static void main(String[] args) {
		int totalStudent = 5;
		Phaser phaser = new Phaser();
		for(int i=0; i<totalStudent; i++){
			new Thread(new StudentProgress(i, phaser)).start();
		}
	}

}

class StudentProgress implements Runnable{
	private int id;
	private Phaser pahser;
	public StudentProgress(int id, Phaser phaser){
		this.id = id;
		this.pahser = phaser;
	}
	@Override
	public void run(){
		System.out.println("starting student "+id);
		this.pahser.register();
		System.out.println("registered student "+id);
		try {
			Thread.sleep(3000*id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.pahser.arriveAndAwaitAdvance();
		System.out.println("starting next phase 2 for "+id);
		try {
			Thread.sleep(3000*id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("starting next phase 3 for "+id);
	}
}