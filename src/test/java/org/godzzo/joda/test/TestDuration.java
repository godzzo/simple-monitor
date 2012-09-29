package org.godzzo.joda.test;

import java.io.File;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;

public class TestDuration {
	@Test
	public void base() {
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 1, 1, 0, 0, 0, 0);
		
		// duration in ms between two instants
		Duration dur = new Duration(start, end);
		
		showDuration(dur, "Between to times");
		
		System.out.println("Duration to string: " + dur.toString());
		
		// calc will be the same as end
		DateTime calc = start.plus(dur);
		
		System.out.println("Calculated: " + calc.toString());
	}

	private void showDuration(Duration dur, String name) {
		System.out.println("Name: "+name);
		
		System.out.println("- Duration in seconds: " + dur.getStandardSeconds());
		System.out.println("- Duration in minutes: " + dur.getStandardMinutes());
		System.out.println("- Duration in hours: " + dur.getStandardHours());
		System.out.println("- Duration in days: " + dur.getStandardDays());
	}
	
	/*
		Gets the value as a String in the ISO8601 duration format including only seconds and milliseconds.
		For example, "PT72.345S" represents 1 minute, 12 seconds and 345 milliseconds.
		
		For more control over the output, see PeriodFormatterBuilder.

		http://joda-time.sourceforge.net/apidocs/org/joda/time/base/AbstractDuration.html#toString()
	 */
	@Test
	public void makeDurations() {
		showDuration(Duration.standardDays(13), "Thirteen Days");
		showDuration(Duration.parse("PT72.345S"), "Thirteen Days");
		showDuration(Duration.parse("PT90S"), "Thirteen Days");
		// showDuration(Duration.parse("PT3M"), "Thirteen Days");
	}
	
	@Test
	public void checkDuration() {
		Duration shorterOne = Duration.standardDays(3);
		Duration longerOne = Duration.standardDays(21);
		
		DateTime start = new DateTime(2004, 12, 25, 0, 0, 0, 0);
		DateTime end = new DateTime(2005, 1, 1, 0, 0, 0, 0);
		
		Duration current = new Duration(start, end);
		
		if (shorterOne.isShorterThan(current)) {
			System.out.println("YEAH It is shorter than the current One :)");
		}
		
		if (longerOne.isLongerThan(current)) {
			System.out.println("YEAH It is longer than the current One :)");
		}
	}
	
	@Test
	public void now() {
		System.out.println("NOW "+new DateTime().toString("yyyy.MM.dd.hh.mm.ss"));
	}
	
	@Test
	public void stepDayCells() {
		DateTime base = new DateTime();
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(base.getYear());
		builder.append(File.separatorChar);
		builder.append(base.getMonthOfYear());
		builder.append(File.separatorChar);
		builder.append(base.getDayOfYear());
		builder.append(File.separatorChar);
		
		System.out.println(builder.toString());
		
//		base.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
		
//		Duration duration = Duration.standardMinutes(20);
		Duration duration = Duration.standardMinutes(60);
		
		DateTime currentDayStart = base.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
		
		DateTime nextDayStart = currentDayStart.plusDays(1);
		
		while (nextDayStart.isAfter(currentDayStart)) {
			System.out.println("currentDayStart " + currentDayStart.toString());
			currentDayStart = currentDayStart.plusSeconds((int) duration.getStandardSeconds());
		}
		
		System.out.println("nextDayStart " + nextDayStart.toString());
	}
	
	@Test
	public void scanBack() {
		DateTime now = DateTime.now();
		int times = 7;
		
		System.out.println("0. NOW : "+now);
		
		for (int i=1; i<=times; i++) {
			DateTime yesterday = now.minusDays(i);
			
			System.out.println(i+". Back: "+yesterday);
		}
		
	}
}
