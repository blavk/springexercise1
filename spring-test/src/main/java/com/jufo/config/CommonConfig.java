package com.jufo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
public class CommonConfig {
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.druid")
	public DruidDataSource getDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

}
