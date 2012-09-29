package org.godzzo.simmon.tool;

import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JmxConnnectorBean {
	private String connectionUrl = "service:jmx:rmi:///jndi/rmi://:9999/jmxrmi";
	private String userName = "user";
	private String userPass = "password";

	private JMXConnector connector;

	public static class ConnectionInfo {
		private JMXConnector connector;
		private MBeanServerConnection connection;
		
		public JMXConnector getConnector() {
			return connector;
		}
		public void setConnector(JMXConnector connector) {
			this.connector = connector;
		}
		public MBeanServerConnection getConnection() {
			return connection;
		}
		public void setConnection(MBeanServerConnection connection) {
			this.connection = connection;
		}
	}
	
	public ConnectionInfo open() throws Exception {
		ConnectionInfo info = new ConnectionInfo();
		Map<String, Object> env = new HashMap<String, Object>(); 
		
		env.put("jmx.remote.credentials", new String[] { getUserName(), getUserPass() });
		
		JMXServiceURL url = new JMXServiceURL(getConnectionUrl());
		info.setConnector(JMXConnectorFactory.connect(url, env));

		info.setConnection(info.getConnector().getMBeanServerConnection());
		
		return info;
	}
	
	public void close(ConnectionInfo info) throws Exception {
		info.getConnector().close();
	}
	
	public String getConnectionUrl() {
		return connectionUrl;
	}
	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public JMXConnector getConnector() {
		return connector;
	}
	public void setConnector(JMXConnector connector) {
		this.connector = connector;
	}
}
