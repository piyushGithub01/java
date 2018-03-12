package com.java.concurrency.example;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
	 
	public class ForkFindBigNumber extends RecursiveTask<Integer> {
	 
	   /**
		 * use of ForkJoinPool - to divide a big tasks to smaller similar task so that multi processor
		 * system can make use of all processor and free worker thread which has finished its tasks can
		 * take up work pending by any other thread - uses work stealing algorithm
		 */
		private static final long serialVersionUID = 5353832367258227021L;
		int[] numbers;
	   int start;
	   int end;
	 
	   int THRESHOLD = 5;
	 
	   public ForkFindBigNumber(int[] numbers) {
	      	this(numbers, 0, numbers.length);
	   }
	 
	 
	   public ForkFindBigNumber(int[] numbers, int start, int end) {
	      this.numbers = numbers;
	      this.start = start;
	      this.end = end;
	   }
	 
	   @Override
	public Integer compute() {
	      int length = end - start;
	      int max = 0;
	      if (length < THRESHOLD) {
	         for (int x = start; x < end; x++) { 
	            if (numbers[x] > max) {
	               max = numbers[x];
	            }
	         }
	         return max;
	      } else {
	         int split = length/2;
	         ForkFindBigNumber left = new ForkFindBigNumber(numbers, start, start + split);
	         left.fork();
	         ForkFindBigNumber right = new ForkFindBigNumber(numbers, start + split, end);
	         right.fork();
//	         return Math.max(right.compute(), left.join());
	         return Math.max(right.join(), left.join());
	      }
	   }
	 
	   public static void main(String[] args) {
	 
	      int SIZE = 5000;
	      final int[] numbers = new int[SIZE];
	      int biggest = 0;
	 
	      for (int i=0; i<SIZE; i++){
	         numbers[i] = (int) (Math.random() * 10000);
	         if ( numbers[i] > biggest ) {
	            biggest = numbers[i];
	         }
	      }
	      System.out.println("Biggest number generated: " + biggest);
	 
	      ForkJoinPool pool = new ForkJoinPool(4);
	      ForkFindBigNumber fbn = new ForkFindBigNumber(numbers);
	      System.out.println("Biggest number found: " + pool.invoke(fbn));
	 
	   }
	}
