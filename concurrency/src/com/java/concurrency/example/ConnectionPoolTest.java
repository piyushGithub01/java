package com.java.concurrency.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionPoolTest {

	/*
	 * use of ArrayBlockingQueue to create database connection pool - or resource pool
	 */
	public static void main(String[] args) throws InterruptedException {
		ConnectionPool p = new ConnectionPool();
		Connection c1 = p.getConnection();
		Connection c2 = p.getConnection();
		Connection c3 = p.getConnection();
		System.out.println("using connection "+c1.messageInConnection());
		System.out.println("using connection "+c2.messageInConnection());
		System.out.println("using connection "+c3.messageInConnection());
		p.givebackConnection(c1);
		p.givebackConnection(c2);
		p.givebackConnection(c3);
		Connection c4 = p.getConnection();
		Connection c5 = p.getConnection();
		System.out.println("using connection "+c4.messageInConnection());
		System.out.println("using connection "+c5.messageInConnection());
		p.givebackConnection(c4);
		p.givebackConnection(c5);
	}

}

class ConnectionPool{
	private static int POOLSIZE = 3;
	private static BlockingQueue<Connection> q = new ArrayBlockingQueue<Connection>(POOLSIZE);
	private AtomicBoolean ini = new AtomicBoolean(true);
	
	public ConnectionPool(){
		initializePool();
	}
	public void initializePool(){
		if(ini.get()){
			while(q.size()<POOLSIZE){
				System.out.println("adding connection");
				q.add(new Connection());
			}
			ini.compareAndSet(true, false);
		}
	}
	public Connection getConnection() throws InterruptedException{
		Connection c = q.take();
		System.out.println("after taking connection size of pool is "+q.size());
		return c;
	}
	public void givebackConnection(Connection c){
		q.add(c);
		System.out.println("size of pool after giveback is "+q.size());
	}
}

class Connection{
	public String messageInConnection(){
		return "using connection";
	}
}