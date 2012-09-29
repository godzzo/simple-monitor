package org.godzzo.spring.test;

import org.godzzo.spring.test.bean.NameAwareBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/name-aware.xml")
public class TestNameAware {
	@Autowired
	private NameAwareBean bean;
	
	@Test
	public void simple() throws Exception {
		System.out.println("BEAN NAME: "+bean.getBeanName());
	}
}
