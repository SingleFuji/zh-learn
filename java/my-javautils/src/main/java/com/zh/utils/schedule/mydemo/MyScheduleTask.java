package com.zh.utils.schedule.mydemo;

import org.joda.time.DateTime;

public class MyScheduleTask implements Runnable{

	private static final String FORMATE_DATE = "yyyy-MM-dd";
    private static final String FORMATE_SECONDS = "HH:mm:ss";
    private static final String FORMATE_FULL = FORMATE_DATE.concat(" ").concat(FORMATE_SECONDS);
	
	public void run() {
		// TODO Auto-generated method stub
		DateTime now = new DateTime();
		System.out.println(now.toString(FORMATE_FULL));
	}

}
