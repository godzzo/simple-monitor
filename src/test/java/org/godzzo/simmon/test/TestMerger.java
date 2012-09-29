package org.godzzo.simmon.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:merger/base.xml")
public class TestMerger {
	@Test
	public void simple() throws Exception {
		Thread.sleep(30*1000);
	}
}
