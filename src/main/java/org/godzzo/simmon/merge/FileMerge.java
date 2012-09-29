package org.godzzo.simmon.merge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.godzzo.simmon.util.file.ScanFileNameItemJob;
import org.joda.time.DateTime;
import org.junit.Assert;

abstract public class FileMerge extends ScanFileNameItemJob {
	private Log log = LogFactory.getLog(FileMerge.class);

	private String writePath;

	private boolean remove = false;

	@Override
	protected void checkParameters() {
		Assert.assertNotNull("WritePath is null!", getWritePath());
	}
	
	@Override
	protected void invokeItem(String name, File item) throws Exception {
		write(name, item);
	}
	
	protected void write(String name, File item) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(item));
		String line;

		while ((line = reader.readLine()) != null) {
			String[] values = line.split(";");

			DateTime time = new DateTime(new Long(values[0]));

			log.debug("TIME: " + time.toString());

			writeValue(name, time, values[1], line);
		}

		reader.close();

		if (isRemove()) {
			item.delete();
		}
	}

	abstract protected void writeValue(String name, DateTime currentTime,
			String string, String line) throws Exception;

	public String getWritePath() {
		return writePath;
	}

	public void setWritePath(String writePath) {
		this.writePath = writePath;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}
}
