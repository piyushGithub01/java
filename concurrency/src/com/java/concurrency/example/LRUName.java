package com.java.concurrency.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LRUName {
	
	/*
	 * Least recently used item cache - using ConcurrentHashMap and ConcurrentLinkedQeueu
	 */

	public static void main(String[] args) {
		LRUNameCache c = new LRUNameCache(3);
		c.putElement("A", "A");
		System.out.println(c);
		c.putElement("B", "B");
		System.out.println(c);
		c.putElement("B", "B");
		System.out.println(c);
		c.putElement("C", "C");
		System.out.println(c);
		c.putElement("D", "D");
		System.out.println(c);
		c.getElement("B");
		System.out.println(c);
		c.putElement("A", "A");
		System.out.println(c);
		c.putElement("A", "A");
		System.out.println(c);
	}

}

class LRUNameCache{
	private int maxSize;
	private ConcurrentHashMap<String, String> map;
	private ConcurrentLinkedQueue<String> queue;
	public LRUNameCache(int maxSize){
		this.maxSize = maxSize;
		this.map = new ConcurrentHashMap<String, String>(maxSize);
		this.queue = new ConcurrentLinkedQueue<String>();
	}
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	public ConcurrentHashMap<String, String> getMap() {
		return map;
	}
	public void setMap(ConcurrentHashMap<String, String> map) {
		this.map = map;
	}
	public ConcurrentLinkedQueue<String> getQueue() {
		return queue;
	}
	public void setQueue(ConcurrentLinkedQueue<String> queue) {
		this.queue = queue;
	}
	@Override
	public String toString() {
		return "LRUNameCache [maxSize=" + maxSize + ", map=" + map + ", queue=" + queue + "]";
	}
	public String getElement(String key){
		String value = null;
		if(map.containsKey(key)){
			queue.remove(key);
			queue.add(key);
			value = map.get(key);
		}
		return value;
	}
	public void putElement(String key, String value){
		if(map.containsKey(key)){
			map.put(key, value);
			queue.remove(key);
			queue.add(key);
		}else{
			if(map.size() >= maxSize){
				String olderKey = queue.poll();
				map.remove(olderKey);
				map.put(key, value);
				queue.add(key);
			}else{
				map.put(key, value);
				queue.add(key);
			}
		}
	}
}
