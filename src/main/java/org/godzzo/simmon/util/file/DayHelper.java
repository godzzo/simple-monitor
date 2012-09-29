package org.godzzo.simmon.util.file;

import java.io.File;

import org.joda.time.DateTime;

public class DayHelper {
	public static StringBuilder makeDayPath(String sourcePath, String name, DateTime base) {
		StringBuilder builder = makeMonthPath(sourcePath, name, base);
		
		builder.append(base.getDayOfMonth());
		builder.append(File.separatorChar);

		return builder;
	}

	public static StringBuilder makeMonthPath(String sourcePath, String name, DateTime base) {
		StringBuilder builder = new StringBuilder();

		builder.append(sourcePath);
		builder.append(File.separatorChar);
		builder.append(name);
		builder.append(File.separatorChar);
		builder.append(base.getYear());
		builder.append(File.separatorChar);
		builder.append(base.getMonthOfYear());
		builder.append(File.separatorChar);
		
		return builder;
	}
}
