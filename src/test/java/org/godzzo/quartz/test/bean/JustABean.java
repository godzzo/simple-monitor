package org.godzzo.quartz.test.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JustABean {
	private Log log = LogFactory.getLog(JustABean.class);
	
	public void doingAScheduledJobToo() {
		log.info(String.format("doingAScheduledJobToo"));
	}
}
