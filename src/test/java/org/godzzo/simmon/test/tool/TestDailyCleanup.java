package org.godzzo.simmon.test.tool;

import org.godzzo.simmon.test.util.BaseHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:tool/daily-cleanup.xml")
public class TestDailyCleanup {
	
	@Test
	public void invoke() throws Exception {
		BaseHelper.waitForEnter();
	}
}
