package com.java.concurrency.example;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class CityProducerConsuerPriority {
	
	/*
	 * producer consumer pattern using PriorityBlockingQueue
	 * 
	 * if capacity of blocking queue is already full, then put operation will be blocked till some other thread
	 * consumes and free a space in queue
	 * 
	 * take operation will be blocked if queue is empty and will run only after other thread has added any 
	 * item in blocking queue
	 */

	public static void main(String[] args) {
		BlockingQueue<City> q = new PriorityBlockingQueue<>(2, new CityComparatorByName());
		CityProducer p = new CityProducer(q);
		CityConsumer c = new CityConsumer(q);
		ExecutorService e = Executors.newFixedThreadPool(2);
		e.execute(p);
		e.execute(c);
		e.shutdown();
	}

}

class CityProducer implements Runnable{
	BlockingQueue<City> q;
	public CityProducer(BlockingQueue<City> q){
		this.q = q;
	}
	@Override 
	public void run(){
		try {
			q.put(new City("Bangalore"));
			System.out.println("added Bangalore");
			q.put(new City("NewYork"));
			System.out.println("added NewYork");
			q.put(new City("AbuDhabi"));
			System.out.println("added AbuDhabi");
			q.put(new City("Mumbai"));
			System.out.println("added Mumbai");
			q.put(new City("Japan"));
			System.out.println("added Japan");
			System.out.println("size of queue is: "+q.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class CityConsumer implements Runnable{
	BlockingQueue<City> q;
	public CityConsumer(BlockingQueue<City> q){
		this.q = q;
	}
	@Override
	public void run(){
		try {
			Thread.sleep(5000);
			while(!q.isEmpty()){
				System.out.println("consuming: "+q.take());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class CityComparatorByName implements Comparator<City>{

	@Override
	public int compare(City o1, City o2) {
		return o1.getName().compareTo(o2.getName());
	}
	
}

class City{
	private String name;
	
	public City(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "City [name=" + name + "]";
	}
	
}
