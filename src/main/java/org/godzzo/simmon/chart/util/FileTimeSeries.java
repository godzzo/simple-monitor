package org.godzzo.simmon.chart.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.util.Log;
import org.junit.Assert;

public class FileTimeSeries {
	private SimpleDateFormat format;
	
	public SimpleDateFormat getFormat() {
		return format;
	}

	public void setFormat(SimpleDateFormat format) {
		this.format = format;
	}

	private String title;
	private String file;

	public TimeSeries load() throws Exception {
		Assert.assertNotNull(getFile());
		
		return load(getFile());
	}
	
	public TimeSeries load(String file) throws Exception {
		Assert.assertNotNull(getTitle());
		
		TimeSeries series = new TimeSeries(getTitle());
		
		load(file, series);
		
		return series;
	}
	
	public void load(String file, TimeSeries series) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;

		while ((line = reader.readLine()) != null) {
			String[] vals = line.split(";");

			try {
				series.add(new Second(parseDate(vals[0])), parseValue(vals[1]));
			} catch (SeriesException e) {
				Log.error(file+" / "+e.getMessage());
			}
		}
		
		reader.close();
	}

	private Date parseDate(String value) throws Exception {
		if (getFormat() == null)
			return new Date(new Long(value));
		else
			return format.parse(value);
	}

	private Double parseValue(String value) throws Exception {
		return new Double(value);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}
