package com.jufo.config;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
public class CommonConfig {
	
	private static final Logger LOG = LoggerFactory.getLogger(CommonConfig.class);
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource.druid")
	public DruidDataSource getDataSource() {
		return DruidDataSourceBuilder.create().build();
	}
	
	@Bean("encryptBean")
	public StringEncryptor stringEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig stringPBEConfig = new SimpleStringPBEConfig();
		stringPBEConfig.setAlgorithm("PBEWithMD5AndDES");
		stringPBEConfig.setPassword("password");
		stringPBEConfig.setPoolSize(1);
		stringPBEConfig.setKeyObtentionIterations(1000);
		stringPBEConfig.setProviderName("SunJCE");
		stringPBEConfig.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		stringPBEConfig.setStringOutputType("base64");
		encryptor.setConfig(stringPBEConfig);
		return encryptor;
	}
	
	@Bean(value="tp1")
	public Executor executors() {
//		Executors.newFixedThreadPool(2);
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.AbortPolicy());
		return threadPoolExecutor;
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void name() {
		LOG.info("start success!");
	}

}
