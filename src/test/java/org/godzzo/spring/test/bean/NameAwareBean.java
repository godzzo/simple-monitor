package org.godzzo.spring.test.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanNameAware;

public class NameAwareBean implements BeanNameAware {

	private static final Log log = LogFactory.getLog(NameAwareBean.class);

	private String name;

	public void setBeanName(String name) {
		this.name = name;
	}

	public String getBeanName() {
		return name;
	}
	
	public void businessMethod() {

		if (log.isInfoEnabled()) {
			log.info("Bean: [" + name + "] - Calling business method.");
		}
	}
}
