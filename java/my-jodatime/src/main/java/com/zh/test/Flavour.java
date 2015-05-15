package com.zh.test;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Period;

import com.makotogroup.joda.factory.SystemFactory;

public class Flavour {
	public boolean isAfterPayDay(DateTime datetime) {
		  if (datetime.getMonthOfYear() == 2) {   // February is month 2!!
		    return datetime.getDayOfMonth() > 26;
		  }
		  return datetime.getDayOfMonth() > 28;
		}

		public Days daysToNewYear(LocalDate fromDate) {
		  LocalDate newYear = fromDate.plusYears(1).withDayOfYear(1);
		  return Days.daysBetween(fromDate, newYear);
		}

		public boolean isRentalOverdue(DateTime datetimeRented) {
		  Period rentalPeriod = new Period().withDays(2).withHours(12);
		  return datetimeRented.plus(rentalPeriod).isBeforeNow();
		}

		public String getBirthMonthText(LocalDate dateOfBirth) {
		  return dateOfBirth.monthOfYear().getAsText(Locale.ENGLISH);
		}
		
		public static void main(String[] args)
		{
			Flavour demo = new Flavour();
			DateTime time = new DateTime(2015, 5, 15, 15, 30, 40, 0);
			System.out.println(time);
			System.out.println(time.toString("dd-MM-yyyy HH:mm:ss"));
			String timeStr = "2015-02-06T11:20:00";
			DateTime strTime = new DateTime(timeStr);
			System.out.println(strTime);
			DateTime time1 = new DateTime();
			System.out.println(time1);
//			DateTime now = SystemFactory.getClock().getDateTime();
			LocalDate now = new LocalDate();
			System.out.println(now);
//			demo.isAfterPayDay(now);
		}
}
