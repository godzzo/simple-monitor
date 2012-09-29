package org.godzzo.simmon.util.file;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.godzzo.simmon.chart.MakeYesterdayChart;
import org.joda.time.DateTime;

public class ScanDayJob extends ScanFileNameJob {
	private Log log = LogFactory.getLog(MakeYesterdayChart.class);
	private int times = 7;
	private int begin = 1;
	private DateTime now;

	@Override
	protected void invokeOnStart() throws Exception {
		setNow(DateTime.now());
	}

	@Override
	public void invokeName(File file) throws Exception {
		String name = file.getName();

		for (int i = getBegin(); i <= getTimes(); i++) {
			DateTime yesterday = getNow().minusDays(i);

			File dayDir = new File(DayHelper.makeDayPath(getSourcePath(), name,
					yesterday).toString());
			File dataFile = getDataFile(name, yesterday);
			File imageFile = getImageFile(name, yesterday);

			invokeDay(name, dayDir, dataFile, imageFile);
		}
	}

	public void invokeDay(String name, File dayDir, File dataFile,
			File imageFile) throws Exception {
		log.info(String.format("Name: %s, DayDir: %s, Data: %s, Image: %s",
				name, 
				dayDir.getCanonicalPath(), 
				dataFile.getCanonicalPath(),
				imageFile.getCanonicalPath()));
	}

	protected File getDataFile(String name, DateTime base) {
		return getFile(name, base, "txt");
	}

	protected File getImageFile(String name, DateTime base) {
		return getFile(name, base, "png");
	}

	protected File getFile(String name, DateTime base, String extension) {
		StringBuilder builder = DayHelper.makeMonthPath(getSourcePath(), name,
				base);

		builder.append(base.getDayOfMonth());
		builder.append('.').append(extension);

		return new File(builder.toString());
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public DateTime getNow() {
		return now;
	}

	public void setNow(DateTime now) {
		this.now = now;
	}
}
