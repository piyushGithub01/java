package com.java.concurrency.example;

import java.time.LocalDateTime;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkFindLongestName extends RecursiveTask<String> {

	/**
	 * use of ForkJoinPool - to divide a big tasks to smaller similar task so that multi processor
	 * system can make use of all processor and free worker thread which has finished its tasks can
	 * take up work pending by any other thread - uses work stealing algorithm
	 */
	private static final long serialVersionUID = -8664884202451884565L;
	private String[] arr;
	private int start;
	private int end;
	private LocalDateTime t;
	private String name;
	public ForkFindLongestName(String[] arr){
		this(arr, 0, arr.length, LocalDateTime.now(),"a");
	}
	
	public ForkFindLongestName(String[] arr, int start, int end, LocalDateTime t, String name){
		this.arr = arr;
		this.start = start;
		this.end = end;
		this.t = t;
		this.name = name;
	}

	public static void main(String[] args) {
		String[] arr = new  String[15];
		arr[0] = "zero";
		arr[1] = "oneiiiiiiiiiiiii";
		arr[2] = "two";
		arr[3] = "three";
		arr[4] = "four";
		arr[5] = "five";
		arr[6] = "six";
		arr[7] = "seven";
		arr[8] = "eight";
		arr[9] = "nine";
		arr[10] = "tenaaaaaaaa";
		arr[11] = "eleven";
		arr[12] = "twelve";
		arr[13] = "thirteen";
		arr[14] = "fourteena";
		ForkJoinPool p = new ForkJoinPool(2);
		ForkFindLongestName f = new ForkFindLongestName(arr);
		System.out.println("Longest name is "+p.invoke(f));
		p.shutdown();
		
	}

	@Override
	protected String compute() {
		String longestName = "";
		int biglength = 0;
		int length=end-start;
			
			if(length < 5){
				for(int i = start; i < end; i++){
					System.out.println("testing "+arr[i]+" at time "+t+" with name "+name+end);
					if(arr[i].length() >= biglength){
						biglength=arr[i].length();
						longestName=arr[i];
					}
				}
				return longestName;
			}else{
				int split = length/2;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ForkFindLongestName x = new ForkFindLongestName(arr, start, start+split, LocalDateTime.now(),"x");
				x.fork();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ForkFindLongestName y = new ForkFindLongestName(arr, start+split, end, LocalDateTime.now(),"y");
				return generate(x.join(), y.invoke());
			}
	}


	private String generate(String join, String join2) {
		if(join.length()>join2.length()){
			return join;
		}else{
			return join2;
		}
	}

}
