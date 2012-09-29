package org.godzzo.simmon.util.file;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

abstract public class ScanFileNameJob extends QuartzJobBean {
	private Log log = LogFactory.getLog(ScanFileNameJob.class);

	private String sourcePath;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		try {
			checkParameters();

			log.debug("START");

			invoke();

			log.debug("FINISH");
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}
	}

	protected void checkParameters() {
		Assert.assertNotNull("SourcePath is null!", getSourcePath());
	}

	public void invoke() throws Exception {
		File[] names = new File(getSourcePath()).listFiles();
		
		if (names != null) {
			invokeOnStart();
			
			Arrays.sort(names);
	
			for (File name : names) {
				if (name.isDirectory()) {
					invokeName(name);
				}
			}
			
			invokeOnFinish();
		} else {
			log.debug("Source not contains any item files: "+getSourcePath());
		}
	}

	protected void invokeOnStart() throws Exception {}
	protected void invokeOnFinish() throws Exception {}

	abstract public void invokeName(File file) throws Exception;

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
}
