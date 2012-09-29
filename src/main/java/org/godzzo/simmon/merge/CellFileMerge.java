package org.godzzo.simmon.merge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

// TODO It is not Finished (maybe never will)
public class CellFileMerge extends DurationBasedMerge {
	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(DurationFileMerge.class);
	
	public CellFileMerge() {
		super();
		
		initCells();
	}
	
	@Override
	protected void writeValue(String name, DateTime currentTime, String string,
			String line) throws Exception {
		
	}
	
	private void initCells() {
		DateTime base = DateTime.now();
		DateTime currentDayStart = base.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
		
		DateTime nextDayStart = currentDayStart.plusDays(1);
		
		while (nextDayStart.isAfter(currentDayStart)) {
			currentDayStart = currentDayStart.plusSeconds((int) getDuration().getStandardSeconds());
		}
		
		System.out.println("nextDayStart " + nextDayStart.toString());
	}

	@SuppressWarnings("unused")
	private void addCell(DateTime time) {
		CellInfo info = new CellInfo(timeValue(time), "");
		
	}

	private int timeValue(DateTime time) {
		return time.getHourOfDay()*3600+time.getMinuteOfHour()*60+time.getSecondOfMinute();
	}
	
	public static class CellInfo {
		int value;
		
		public CellInfo(int value, String show) {
			this.value = value;
		}
	}
}
