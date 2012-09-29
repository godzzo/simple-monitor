package org.godzzo.simmon.merge;

import org.godzzo.simmon.util.DurationBean;
import org.joda.time.Duration;
import org.junit.Assert;

abstract public class DurationBasedMerge extends FileMerge {
	private DurationBean durationBean;

	@Override
	protected void checkParameters() {
		Assert.assertNotNull("Duration is null!", getDurationBean());
	}

	protected boolean isShorterThan(Duration duration) {
		return getDurationBean().getDuration().isShorterThan(duration);
	}
	
	public Duration getDuration() {
		return getDurationBean().getDuration();
	}
	
	public DurationBean getDurationBean() {
		return durationBean;
	}

	public void setDurationBean(DurationBean durationBean) {
		this.durationBean = durationBean;
	}
}
