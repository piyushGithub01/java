package com.java.concurrency.example;

public class LongRunningThread {

	public static void main(String[] args) throws InterruptedException {
		Thread.currentThread().setName("PiyusLongRunning");
		System.out.println("Long Running Thread");
		String str = "start";
		for(int i = 0; i < 10000; i++){
			str = str +" "+ i +" ";
			System.out.println(str);
//			Thread.sleep(10000);
			for(int j=0; j<99999999; j++){
				
			}
		}
	}

}
