package com.jufo.spring_test;

import java.text.ParseException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Sets;
import com.jufo.app.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {Application.class})
public class ScheduleTest {
	
	@Autowired
	SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired
	JobDetailFactoryBean jobDetailFactoryBean;
	
	Scheduler scheduler;
	
	@Before
	public void name() {
		scheduler = schedulerFactoryBean.getScheduler();
		
	}
	
	@Test
	public void test1() throws SchedulerException, ParseException, InterruptedException {
		jobDetailFactoryBean.setDurability(false);
		JobDetail jobDetail = jobDetailFactoryBean.getObject();
		jobDetail.getJobDataMap().put("jobid", 1);
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setCronExpression("0/5 * * * * ?");
		cronTriggerFactoryBean.setName("job test");
		cronTriggerFactoryBean.afterPropertiesSet();
		CronTrigger cronTrigger = cronTriggerFactoryBean.getObject();
		cronTrigger.getKey();
		scheduler.scheduleJob(jobDetail, (Set<? extends Trigger>) Sets.newHashSet(), true);
		TimeUnit.SECONDS.sleep(60);
	}
	
	@Test
	public void deleteTest() throws SchedulerException {
		JobDetail jobDetail = jobDetailFactoryBean.getObject();
		System.out.println(jobDetail.getKey());
		scheduler.deleteJob(jobDetail.getKey());
		
	}

}
