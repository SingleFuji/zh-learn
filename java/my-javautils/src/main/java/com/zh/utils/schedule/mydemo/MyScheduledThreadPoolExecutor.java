package com.zh.utils.schedule.mydemo;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;

public class MyScheduledThreadPoolExecutor {
	
	private static long initialDelay=5000;
    private static long period=3000;

	public static void excuteSchedule()
	{
		 ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		 System.out.println("START:"+new DateTime());
		 exec.scheduleAtFixedRate(new MyScheduleTask(), initialDelay, period, TimeUnit.MILLISECONDS);
	}
	
	public static void main(String[] args) {
		excuteSchedule();
	}
}
