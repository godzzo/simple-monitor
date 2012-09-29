package org.godzzo.simmon.sampler;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import org.godzzo.simmon.tool.JmxConnnectorBean;
import org.godzzo.simmon.tool.JmxConnnectorBean.ConnectionInfo;

public class JmxSampler extends AbstractSampler {
	private String objectName = "Catalina:type=GlobalRequestProcessor,name=\"http-bio-9080\"";
	private String attributeName = "requestCount";
	
	private JmxConnnectorBean connector;

	@Override
	public String getValue() throws Exception {
		ConnectionInfo info = getConnector().open();
		
		String value = getValue(info.getConnection());
		
		getConnector().close(info);
		
		return value;
	}
	
	private String getValue(MBeanServerConnection mbsc) throws Exception {
		ObjectName beanName = new ObjectName(getObjectName());
		
		Object value = mbsc.getAttribute(beanName, attributeName);
		
		return value.toString();
	}
	
	
	public JmxConnnectorBean getConnector() {
		return connector;
	}
	public void setConnector(JmxConnnectorBean connector) {
		this.connector = connector;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
}
