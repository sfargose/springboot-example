package com.military.content.articles;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAutoConfiguration(exclude={BatchAutoConfiguration.class})
@ComponentScan
@EnableScheduling
@EnableBatchProcessing
@ImportResource(value={
		"classpath:/databaseContext.xml",
		"classpath:/applicationContext.xml",
		"classpath:/batchConfig.xml"
		} )
public class Application {

    public static void main(String[] args) {
    	System.setProperty("spring.batch.job.enabled", "false"); //To disable the auto start of all jobs when application starts
    	System.setProperty("spring.batch.initializer.enabled", "false");
    	
        SpringApplication.run(Application.class, args);
        
    }

}