package com.jufo.schedule.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisallowConcurrentExecution
public class TestJob implements Job{

	private static final Logger LOG = LoggerFactory.getLogger(TestJob.class);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOG.info("test job start");
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		jobDataMap.forEach((k, v) -> {
			System.out.println(k + ": " + v);
		});
		LOG.info("test job finish");
	}

}
