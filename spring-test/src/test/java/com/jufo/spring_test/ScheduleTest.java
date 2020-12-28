package com.jufo.spring_test;

import java.text.ParseException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
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
import com.mchange.v2.c3p0.ComboPooledDataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {Application.class})
public class ScheduleTest {
	
	@Autowired
	SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired
	JobDetailFactoryBean jobDetailFactoryBean;
	
	
	@Test
	public void test1() throws SchedulerException, ParseException, InterruptedException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		jobDetailFactoryBean.setDurability(false);
		JobDetail jobDetail = jobDetailFactoryBean.getObject();
		jobDetail.getJobDataMap().put("jobid", 1);
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setCronExpression("0/5 * * * * ?");
		cronTriggerFactoryBean.setName("job test");
		cronTriggerFactoryBean.afterPropertiesSet();
		scheduler.scheduleJob(jobDetail, (Set<? extends Trigger>) Sets.newHashSet(cronTriggerFactoryBean.getObject()), true);
		TimeUnit.SECONDS.sleep(60);
	}

}
