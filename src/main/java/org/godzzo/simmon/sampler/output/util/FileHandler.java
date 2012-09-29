package org.godzzo.simmon.sampler.output.util;

import java.io.File;
import java.io.FileOutputStream;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class FileHandler {
	private File file;

	private DateTime start;
	
	public FileHandler(String path, String mask) {
		this("data", path, mask, new DateTime());
	}
	
	public FileHandler(String tag, String path, String mask, DateTime time) {
		super();
		
		path += tag+"."+time.toString(mask)+".txt";
		
		this.file = new File(path);
		this.start = time;
	}
	
	public Duration getDuration() {
		return getDuration(new DateTime());
	}
	
	public Duration getDuration(DateTime end) {
		return new Duration(start, end);
	}
	
	public void write(String data) throws Exception {
		FileOutputStream stream = new FileOutputStream(file, true);
		
		stream.write(data.getBytes());
		stream.close();
	}
	
	public long size() {
		return file.length();
	}
	
	public File getFile() {
		return file;
	}
}
