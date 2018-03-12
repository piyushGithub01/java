package com.java.concurrency.example;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkFindLargestNumber extends RecursiveTask<Integer>{

	/**
	 * use of ForkJoinPool - to divide a big tasks to smaller similar task so that multi processor
	 * system can make use of all processor and free worker thread which has finished its tasks can
	 * take up work pending by any other thread - uses work stealing algorithm
	 */
	private static final long serialVersionUID = 8529083226362865704L;
	
	private int start;
	private int end;
	private int[] arr;
	
	public ForkFindLargestNumber(int[] arr){
		this(arr, 0, arr.length);
	}
	public ForkFindLargestNumber(int[] arr, int start, int end){
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	public static void main(String[] args) {
		int max = 5000;
		int[] arr = new int[max];
		int biggest = 0;
		for(int i=0; i<max; i++){
			arr[i] = (int) (Math.random()*10000);
			if(arr[i]>biggest){
				biggest = arr[i];
			}
		}
		System.out.println("biggest int set to arr is "+biggest);
		ForkJoinPool p = new ForkJoinPool();
		ForkFindLargestNumber l = new ForkFindLargestNumber(arr);
		System.out.println("biggest number from search is "+p.invoke(l));
		p.shutdown();
		
	}
	
	@Override
	public Integer compute(){
		int length = end - start;
		int threshold = 5;
		int big=0;
		if(length < threshold){
			for(int i=start; i<end; i++){
				if(arr[i]>big){
					big = arr[i];
				}
			}
			return big;
		}else{
			int split = length/2;
			ForkFindLargestNumber left = new ForkFindLargestNumber(arr, start, start + split);
			left.fork();
			ForkFindLargestNumber right = new ForkFindLargestNumber(arr, start+split, end);
			return Math.max(left.join(), right.compute());
		}
	}

}
