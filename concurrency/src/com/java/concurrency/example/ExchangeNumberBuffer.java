package com.java.concurrency.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class ExchangeNumberBuffer {
	/*
	 * use of Exchanger - to exchange content at certain point in coding logic
	 * 
	 * thread 1 read data in buffer
	 * once exchange is called between two threads where thread 1 has put data in buffer and thread 2 has
	 * empty buffer read to read data,
	 * post exchange thread 2 will have buffer where thread 1 had already read some content, so thread 2 
	 * will process it
	 * post exchange thread 1 will have empty buffer created by thread 2, so that thread 1 can read next 
	 * set of files in buffer
	 */

	public static void main(String[] args) {
		List<Integer> l = new ArrayList<>();
		List<Integer> f = new ArrayList<>();
		f.add(0);
		Exchanger<List> ex = new Exchanger<>();
		new Thread(new AddDataToBuffer(l, ex)).start();
		new Thread(new RemoveDataFromBuffer(f, ex)).start();
	}

}

class AddDataToBuffer implements Runnable{
	private List<Integer> l;
	private Exchanger<List> ex;
	public AddDataToBuffer(List<Integer> l, Exchanger<List> ex){
		this.l = l;
		this.ex = ex;
	}
	@Override 
	public void run(){
		int i = 0;
		while(i < 2){
			System.out.println("inside add l not null");
			while(l.size()<3){
				System.out.println("adding data 0");
				l.add(0);
			}
			try {
				Thread.sleep(5000);
				l = ex.exchange(l);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		System.out.println("end of add");
	}
}


class RemoveDataFromBuffer implements Runnable{
	private List<Integer> l;
	private Exchanger<List> ex;
	public RemoveDataFromBuffer(List<Integer> l, Exchanger<List> ex){
		this.l = l;
		this.ex = ex;
	}
	@Override 
	public void run(){
		int i = 0;
		while(i<2){
			System.out.println("inside remove l not null");
			if(l.size() != 0){
				l.stream().forEach(System.out::print);
				l = new ArrayList<Integer>();
			}
			try {
				Thread.sleep(5000);
				l = ex.exchange(l);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		System.out.println("end of remove");
	}
}