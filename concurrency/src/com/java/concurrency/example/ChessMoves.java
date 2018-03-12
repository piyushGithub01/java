package com.java.concurrency.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChessMoves {
	
	/*
	 * running two thread alternative - using wait and noitfy
	 */

	public static void main(String[] args) {
		Chess c = new Chess();
		WhitePlayer w = new WhitePlayer(c);
		BlackPlayer b = new BlackPlayer(c);
		ExecutorService e = Executors.newFixedThreadPool(2);
		e.execute(b);
		e.execute(w);
		e.shutdown();
	}

}

class WhitePlayer implements Runnable{
	private Chess c;
	public WhitePlayer(Chess c){
		this.c = c;
	}
	@Override
	public void run(){
		System.out.println("whitePlayer");
		while(true){
			synchronized(c){
				if(c.getLastMove().equals("WHITE")){
					try {
						c.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					c.makeWhiteMove();
					System.out.println("WHITE MOVED");
					c.notifyAll();
				}
			}
		}
	}
}

class BlackPlayer implements Runnable{
	private Chess c;
	public BlackPlayer(Chess c){
		this.c = c;
	}
	@Override
	public void run(){
		System.out.println("blackPlayer");
		while(true){
			synchronized(c){
				if(c.getLastMove().equals("BLACK")){
					try {
						c.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					c.makeBlackMove();
					System.out.println("BLACK MOVED");
					c.notifyAll();
				}
			}
		}
	}
}

class Chess{
	private String lastMove;
	public Chess(){
		lastMove = "BLACK";
	}
	public void makeWhiteMove() {
		setLastMove("WHITE");
	}
	public void makeBlackMove() {
		setLastMove("BLACK");
	}
	public String getLastMove() {
		return lastMove;
	}
	public void setLastMove(String lastMove) {
		this.lastMove = lastMove;
	}
	
}
