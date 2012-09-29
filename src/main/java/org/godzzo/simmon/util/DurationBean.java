package org.godzzo.simmon.util;

import org.joda.time.Duration;

public class DurationBean {
	private Duration duration;

	public void setDays(long days) {
		setDuration(Duration.standardDays(days));
	}
	public void setHours(long hours) {
		setDuration(Duration.standardHours(hours));
	}
	public void setMinutes(long minutes) {
		setDuration(Duration.standardMinutes(minutes));
	}
	public void setSeconds(long seconds) {
		setDuration(Duration.standardSeconds(seconds));
	}
	public void setMillis(long millis) {
		setDuration(Duration.millis(millis));
	}
	public void setPtsFormat(String value) {
		setDuration(Duration.parse(value));
	}
	
	public Duration getDuration() {
		return duration;
	}
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
}
