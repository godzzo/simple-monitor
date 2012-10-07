package org.godzzo.simmon.sampler;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.godzzo.simmon.sampler.output.Output;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

abstract public class AbstractSampler extends QuartzJobBean implements Sampler {
	private Log log = LogFactory.getLog(getClass());

	private Output output;
	private String name;

	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		try {
			write();
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}
	}

	public void write() throws Exception {
		String data = new Date().getTime() + ";" + acquireValue() + "\n";
		getOutput().write(getName(), data);
	}

	private String acquireValue() {
		try {
			return getValue();
		} catch (Exception e) {
			log.error(getName() + " sampler - " + e.getMessage(), e);

			return "#E";
		}
	}

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
