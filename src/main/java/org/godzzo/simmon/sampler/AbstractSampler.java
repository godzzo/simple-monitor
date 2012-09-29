package org.godzzo.simmon.sampler;

import java.util.Date;

import org.godzzo.simmon.sampler.output.Output;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

abstract public class AbstractSampler extends QuartzJobBean implements Sampler {
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
		String data = new Date().getTime() + ";" + getValue() + "\n";
		getOutput().write(getName(), data);
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
