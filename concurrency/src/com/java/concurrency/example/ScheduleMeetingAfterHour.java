package com.java.concurrency.example;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleMeetingAfterHour {
	/*
	 * use of SchedularExecutorService to schedule a job at fix date time
	 */

	public static void main(String[] args) {
		MeetingAfterHour m1 = new MeetingAfterHour("1");
		MeetingAfterHour m2 = new MeetingAfterHour("2");
		ScheduledExecutorService e = Executors.newScheduledThreadPool(2);
		System.out.println("time now is "+new Date());
		e.schedule(m1, 10, TimeUnit.SECONDS);
		e.schedule(m2, 20, TimeUnit.SECONDS);
		e.shutdown();
	}

}

class MeetingAfterHour implements Runnable{
	private String meetingId;
	public MeetingAfterHour(String meetingId){
		this.meetingId = meetingId;
	}
	@Override 
	public void run(){
		System.out.println("Meeting "+meetingId+" started at "+new Date());
	}
}
