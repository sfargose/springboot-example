package com.military.content.articles.batch;

import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.military.content.articles.metrics.ArticlesImportMetrics;

@Component
@EnableBatchProcessing
public class ImportArticlesJob {
	private Logger log = Logger.getLogger(this.getClass());


	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Autowired
	private ArticlesImportMetrics metricsProvider;

	public void launchJob(){
		boolean fail = true;
		final Object timer = metricsProvider.startTimer();
		try {			
			JobParameters param = new JobParametersBuilder()
					.addString("tempFolder", ""+new Random().nextInt(10000))
					.addDate("date", new Date()) //to avoid JobInstanceAlreadyCompleteException
					.toJobParameters();

			jobLauncher.run(job, param);
			fail=false;
		} catch (Exception e) {
			log.error("Error executing the job",e);
		}
		finally {
			metricsProvider.endTimer(timer);
			if(fail) {
				metricsProvider.markFailure();
			} else {
				metricsProvider.markSuccess();
			}
			metricsProvider.markJob();
		}
	}

}
