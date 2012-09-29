package org.godzzo.quartz.test.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class NormalJob extends QuartzJobBean {
	private Log log = LogFactory.getLog(NormalJob.class);
	
	private String shortName;

	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		log.info(String.format("The Short Name is: %s", getShortName()));
	}
	
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
}
