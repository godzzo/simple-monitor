package org.godzzo.simmon.sampler.output;

import org.godzzo.simmon.sampler.output.util.FileHandler;
import org.godzzo.simmon.util.DurationBean;
import org.joda.time.Duration;

public class DurationFileOutput extends FileOutput {
	private DurationBean openDuration;
	
	@Override
	protected boolean isFilled(FileHandler handler) {
		return getDuration().isShorterThan(handler.getDuration());
	}

	public Duration getDuration() {
		return openDuration.getDuration();
	}

	public void setOpenDuration(DurationBean openDuration) {
		this.openDuration = openDuration;
	}
}
