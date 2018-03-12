package com.java.concurrency.example;

import java.util.List;

public class TrafficRules implements Runnable{

	private List<TrafficPost> p;
	
	public TrafficRules(List<TrafficPost> p){
		this.p = p;
	}
	
	@Override
	public void run() {
		while(true){
			changeColor();
		}
	}

	private void changeColor() {
		System.out.println(p.get(0).getColor() + "   " +
				p.get(1).getColor() +  "   " +
				p.get(2).getColor() +  "   " +
				p.get(3).getColor() );
		for(TrafficPost a : p){
			a.setColor("GREEN");
			System.out.println(p.get(0).getColor() + "   " +
					p.get(1).getColor() +  "   " +
					p.get(2).getColor() +  "   " +
					p.get(3).getColor() );
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			a.setColor("RED");
			System.out.println(p.get(0).getColor() + "   " +
					p.get(1).getColor() +  "   " +
					p.get(2).getColor() +  "   " +
					p.get(3).getColor() );
		}
	}

}
