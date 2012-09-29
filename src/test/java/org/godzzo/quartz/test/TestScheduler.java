package org.godzzo.quartz.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:quartz/test-scheduler.xml")
public class TestScheduler {
	@Test
	public void simple() throws Exception {
		Thread.sleep(3600*1000);
	}
}

/*
	INFO : org.quartz.impl.StdSchedulerFactory - Using default implementation for ThreadExecutor
	INFO : org.quartz.core.SchedulerSignalerImpl - Initialized Scheduler Signaller of type: class org.quartz.core.SchedulerSignalerImpl
	INFO : org.quartz.core.QuartzScheduler - Quartz Scheduler v.2.1.6 created.
	INFO : org.quartz.simpl.RAMJobStore - RAMJobStore initialized.
	
	INFO : org.quartz.core.QuartzScheduler - Scheduler meta-data: Quartz Scheduler (v2.1.6) 'org.springframework.scheduling.quartz.SchedulerFactoryBean#0' with instanceId 'NON_CLUSTERED'
	  Scheduler class: 'org.quartz.core.QuartzScheduler' - running locally.
	  NOT STARTED.
	  Currently in standby mode.
	  Number of jobs executed: 0
	  Using thread pool 'org.quartz.simpl.SimpleThreadPool' - with 10 threads.
	  Using job-store 'org.quartz.simpl.RAMJobStore' - which does not support persistence. and is not clustered.
	
	
	INFO : org.quartz.impl.StdSchedulerFactory - Quartz scheduler 'org.springframework.scheduling.quartz.SchedulerFactoryBean#0' initialized from an externally provided properties instance.
	INFO : org.quartz.impl.StdSchedulerFactory - Quartz scheduler version: 2.1.6
	
	INFO : org.quartz.core.QuartzScheduler - JobFactory set to: org.springframework.scheduling.quartz.AdaptableJobFactory@1b0deb5f
	
	INFO : org.springframework.context.support.DefaultLifecycleProcessor - Starting beans in phase 2147483647
	INFO : org.springframework.scheduling.quartz.SchedulerFactoryBean - Starting Quartz Scheduler now
	
	INFO : org.quartz.core.QuartzScheduler - Scheduler org.springframework.scheduling.quartz.SchedulerFactoryBean#0_$_NON_CLUSTERED started.
	
	INFO : org.godzzo.quartz.test.bean.NormalJob - The Short Name is: Joe
	INFO : org.godzzo.quartz.test.bean.JustABean - doingAScheduledJobToo
	INFO : org.godzzo.quartz.test.bean.NormalJob - The Short Name is: Joe
	INFO : org.godzzo.quartz.test.bean.JustABean - doingAScheduledJobToo
*/
