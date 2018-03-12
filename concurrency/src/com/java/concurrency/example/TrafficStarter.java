package com.java.concurrency.example;

import java.util.ArrayList;
import java.util.List;

public class TrafficStarter {
	/*
	 * Traffic signal simulation using List of TrafficPosts
	 */

	public static void main(String[] args) {

		//supply thread with list of posts
		TrafficPost p1 = new TrafficPost();
		TrafficPost p2 = new TrafficPost();
		TrafficPost p3 = new TrafficPost();
		TrafficPost p4 = new TrafficPost();
		
		List<TrafficPost> p = new ArrayList<TrafficPost>();
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		
		//create new thread and submit
		TrafficRules r = new TrafficRules(p);
		Thread t = new Thread(r);
		t.start();
	}

}
