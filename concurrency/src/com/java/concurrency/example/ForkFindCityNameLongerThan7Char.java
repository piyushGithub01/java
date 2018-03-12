package com.java.concurrency.example;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkFindCityNameLongerThan7Char extends RecursiveTask<List<String>>{

	/**
	 * use of ForkJoinPool - to divide a big tasks to smaller similar task so that multi processor
	 * system can make use of all processor and free worker thread which has finished its tasks can
	 * take up work pending by any other thread - uses work stealing algorithm
	 */
	private static final long serialVersionUID = 1560640468801392990L;
	private int start;
	private int end;
	private String[] arr;
	public ForkFindCityNameLongerThan7Char(String[] arr){
		this(arr, 0, arr.length);
	}
	public ForkFindCityNameLongerThan7Char(String[] arr, int start, int end){
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	public static void main(String[] args){
		int size = 10;
		String[] arr = new String[size];
		arr[0] = "London";
		arr[1] = "Mumbai";
		arr[2] = "AbuDhabhi";
		arr[3] = "Macau";
		arr[4] = "Shanghai";
		arr[5] = "Singapore";
		arr[6] = "Tokyo";
		arr[7] = "Washington";
		arr[8] = "Brussel";
		arr[9] = "Gaza";
		
		ForkJoinPool p = new ForkJoinPool();
		ForkFindCityNameLongerThan7Char c = new ForkFindCityNameLongerThan7Char(arr);
		System.out.println("List of city longer than 7 char");
		List<String> l = p.invoke(c);
		l.stream().forEach((a)->{System.out.println(a);});
		
	}
	
	@Override
	public List<String> compute(){
		int max = 8;
		int length = end - start;
		List<String> result = new CopyOnWriteArrayList<String>();
		List<ForkFindCityNameLongerThan7Char> task = new CopyOnWriteArrayList<>();
		if(arr.length<max){
			for(int i=start; i<end; i++){
				if(arr[i].length()>7){
					result.add(arr[i]);
				}
			}
		}else{
			int split = length / 2;
			ForkFindCityNameLongerThan7Char left = new ForkFindCityNameLongerThan7Char(arr, start, start+split);
			left.fork();
			task.add(left);
			ForkFindCityNameLongerThan7Char right = new ForkFindCityNameLongerThan7Char(arr, start+split, end);
			right.fork();
			task.add(right);
		}
		getStringList(result, task);
		return result;
	}
	
	public void getStringList(List<String> result, List<ForkFindCityNameLongerThan7Char> task){
		for(ForkFindCityNameLongerThan7Char f : task){
			List<String> l = f.join();
			result.addAll(l);
		}
	}
}
