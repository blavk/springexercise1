package com.jufo.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.utils.DBConnectionManager;
import org.quartz.utils.HikariCpPoolingConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.alibaba.druid.pool.DruidDataSource;
import com.jufo.schedule.job.TestJob;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class ScheduleConfig {
	
//	@QuartzDataSource
//	@Bean(value="myDS")
//	@ConfigurationProperties(prefix="spring.c3p0")
//	public ComboPooledDataSource comboPooledDataSource() {
//		return new ComboPooledDataSource();
//	}
	
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("quartz.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		schedulerFactoryBean.setQuartzProperties(properties);
		schedulerFactoryBean.setStartupDelay(2);
		schedulerFactoryBean.setOverwriteExistingJobs(true);
		schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
		schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
		return schedulerFactoryBean;
	}
	
	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean() {
		JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
		jobDetailFactoryBean.setJobClass(TestJob.class);
		jobDetailFactoryBean.setGroup("defaultGrp");
		jobDetailFactoryBean.setName("defaultName");
		jobDetailFactoryBean.setDurability(true);
		return jobDetailFactoryBean;
		
	}
}
