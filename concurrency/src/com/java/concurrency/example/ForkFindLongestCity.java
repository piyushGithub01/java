package com.java.concurrency.example;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkFindLongestCity extends RecursiveTask<String>{

	/**
	 * use of ForkJoinPool - to divide a big tasks to smaller similar task so that multi processor
	 * system can make use of all processor and free worker thread which has finished its tasks can
	 * take up work pending by any other thread - uses work stealing algorithm
	 */
	private static final long serialVersionUID = -976219899296788147L;
	private int start;
	private int end;
	private String[] arr;
	
	public ForkFindLongestCity(String[] arr){
		this(arr, 0, arr.length);
	}
	public ForkFindLongestCity(String[] arr, int start, int end){
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	public static void main(String[] args) {
		String[] arr = new String[10];
		arr[0] = "London";
		arr[1] = "Mumbai";
		arr[2] = "AbuDhabi";
		arr[3] = "Macau";
		arr[4] = "Tokyo";
		arr[5] = "Shanghai";
		arr[6] = "Singapore";
		arr[7] = "Brussels";
		arr[8] = "Washington";
		arr[9] = "Sydney";
		ForkJoinPool p = new ForkJoinPool(4);
		ForkFindLongestCity c = new ForkFindLongestCity(arr);
		System.out.println("Longest city is "+p.invoke(c));
	}
	
	@Override
	public String compute(){
		int length = end - start;
		String longestCity = "";
		int threshold = 5;
		int cityLength = 0;
		if(length < threshold){
			for(int i = start; i<end; i++){
				if(arr[i].length()>=cityLength){
					longestCity=arr[i];
					cityLength = longestCity.length();
				}
			}
			return longestCity;
		}else{
			int split = length/2;
			ForkFindLongestCity left = new ForkFindLongestCity(arr, start, start+split);
			left.fork();
			ForkFindLongestCity right = new ForkFindLongestCity(arr, start+split, end);
			right.fork();
			return checkLongest(left.join(), right.join());
		}
	}
	private String checkLongest(String join, String compute) {
		if(join.length()>compute.length()){
			return join;
		}else{
			return compute;
		}
	}

}
