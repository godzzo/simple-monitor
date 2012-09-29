package org.godzzo.simmon.merge;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.godzzo.simmon.sampler.output.util.FileHandler;
import org.joda.time.DateTime;

public class DurationFileMerge extends DurationBasedMerge {
	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(DurationFileMerge.class);

	private String timeMask = "yyyy.MM.dd.hh.mm.ss";
	
	private FileHandler handler;

	@Override
	protected void writeValue(String name, DateTime currentTime, String string,
			String line) throws Exception {
		if (getHandler() == null) {
			createHandler(name, currentTime);
		} else if (isShorterThan(getHandler().getDuration(currentTime))) {
			closeHandler();

			createHandler(name, currentTime);
		}

		getHandler().write(line + "\n");
	}

	@Override
	protected void cleanUpByName() throws Exception {
		closeHandler();
	}
	
	private void closeHandler() throws Exception {
		if (getHandler() != null) {
			File target = new File(getHandler().getFile().getCanonicalPath()
					.replaceAll("current_write", "data"));

			getHandler().getFile().renameTo(target);

			setHandler(null);
		}
	}

	private void createHandler(String name, DateTime time) {
		String path = getWritePath() + File.separator + name + File.separator;

		(new File(path)).mkdirs();

		setHandler(new FileHandler("current_write", path, getTimeMask(), time));
	}

	public String getTimeMask() {
		return timeMask;
	}

	public void setTimeMask(String timeMask) {
		this.timeMask = timeMask;
	}

	public FileHandler getHandler() {
		return handler;
	}

	public void setHandler(FileHandler handler) {
		this.handler = handler;
	}
}
