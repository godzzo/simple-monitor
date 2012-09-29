package org.godzzo.simmon.tool;

import javax.management.ObjectName;

import org.godzzo.simmon.tool.JmxConnnectorBean.ConnectionInfo;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class JmxOperation extends QuartzJobBean {
	private String objectName = "Catalina:type=GlobalRequestProcessor,name=\"http-bio-9080\"";
	private String operationName = "resetCounters";
	
	private JmxConnnectorBean connector;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		try {
			invoke();
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}
	}

	public void invoke() throws Exception {
		ConnectionInfo info = getConnector().open();
		
		ObjectName beanName = new ObjectName(getObjectName());
		
		info.getConnection().invoke(beanName, getOperationName(), null, null);
		
		getConnector().close(info);
	}
	
	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public JmxConnnectorBean getConnector() {
		return connector;
	}
	public void setConnector(JmxConnnectorBean connector) {
		this.connector = connector;
	}
}
