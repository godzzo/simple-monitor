package org.godzzo.simmon.merge;

import java.io.File;
import java.io.FileOutputStream;

import org.joda.time.DateTime;

public class HourFileMerge extends FileMerge {
	
	
	@Override
	protected void writeValue(String name, DateTime currentTime, String string,
			String line) throws Exception {
		String path = calculateDayPath(name, currentTime);
		
		FileOutputStream stream = new FileOutputStream(path , true);
		stream.write(line.getBytes());
		stream.write('\n');
		stream.close();
	}
	
	private String calculateDayPath(String name, DateTime base) {
		StringBuilder builder = new StringBuilder();
		
		builder.append(getWritePath());
		builder.append(File.separatorChar);
		builder.append(name);
		builder.append(File.separatorChar);
		builder.append(base.getYear());
		builder.append(File.separatorChar);
		builder.append(base.getMonthOfYear());
		builder.append(File.separatorChar);
		builder.append(base.getDayOfMonth());
		builder.append(File.separatorChar);
		
		String path = builder.toString();
		
		(new File(path)).mkdirs();
		
		builder.append(base.getHourOfDay());
		builder.append(".txt");
		
		path = builder.toString();
		
		return path;
	}
}
