package com.java.concurrency.example;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleChessAlarmFixRate {
	/*
	 * use of SchedularExecutorService to run a job at regular interval
	 */

	public static void main(String[] args) {
		ChessAlarm a = new ChessAlarm();
		ScheduledExecutorService e = Executors.newScheduledThreadPool(1);
		System.out.println("Scheduling task at time : "+new Date());
		e.scheduleWithFixedDelay(a, 5, 10, TimeUnit.SECONDS);
//		e.scheduleAtFixedRate(a, 5, 10, TimeUnit.SECONDS);
		try {
            TimeUnit.MILLISECONDS.sleep(30000);
        } 
        catch (InterruptedException e1) {
            e1.printStackTrace();
        }
		e.shutdown();
	}

}

class ChessAlarm implements Runnable{
	@Override
	public void run(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Alarm for chess move at : "+new Date());
	}
}
