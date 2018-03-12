package com.java.concurrency.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LRUObject {
	
	/*
	 * Least recently Used cache - generic 
	 */

	public static void main(String[] args) {
		LRUObjectCache<String, String> c = new LRUObjectCache<String, String>(3);
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

class LRUObjectCache<Key, Value>{
	private int maxSize;
	private ConcurrentHashMap<Key, Value> map;
	private ConcurrentLinkedQueue<Key> queue;
	public LRUObjectCache(int maxSize){
		this.maxSize = maxSize;
		this.map = new ConcurrentHashMap<Key, Value>(maxSize);
		this.queue = new ConcurrentLinkedQueue<Key>();
	}
	public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	public ConcurrentHashMap<Key, Value> getMap() {
		return map;
	}
	public void setMap(ConcurrentHashMap<Key, Value> map) {
		this.map = map;
	}
	public ConcurrentLinkedQueue<Key> getQueue() {
		return queue;
	}
	public void setQueue(ConcurrentLinkedQueue<Key> queue) {
		this.queue = queue;
	}
	@Override
	public String toString() {
		return "LRUObjectCache [maxSize=" + maxSize + ", map=" + map + ", queue=" + queue + "]";
	}
	public Value getElement(Key k){
		Value v = null;
		if(map.containsKey(k)){
			v = map.get(k);
			queue.remove(k);
			queue.add(k);
		}
		return v;
	}
	public void putElement(Key k, Value v){
		if(map.containsKey(k)){
			queue.remove(k);
		}else{
			while(map.size() >= maxSize){
				Key olderKey = queue.poll();
				map.remove(olderKey);
			}
		}
		map.put(k, v);
		queue.add(k);
	}
}
