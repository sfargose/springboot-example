package com.military.content.articles.metrics;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bealetech.metrics.reporting.Statsd;
import com.bealetech.metrics.reporting.StatsdReporter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

@Component
public class StatsDMetricsProvider implements ArticlesImportMetrics{
	
	@Value("${com.military.articles.import.statsd.hostname}")
	private String statsdHostname;
	
	@Value("${com.military.articles.import.statsd.port}")
	private int statsdPort;
	
	private Statsd statsd;
	private MetricRegistry registry;
	private StatsdReporter reporter;

	@PostConstruct
	public void init() {
		registry = new MetricRegistry();
		statsd = new Statsd(statsdHostname, statsdPort);
		reporter = StatsdReporter.forRegistry(registry)
		         .prefixedWith("military.articles.import")
		         .convertDurationsTo(TimeUnit.MILLISECONDS)
		         .convertRatesTo(TimeUnit.SECONDS)
		         .filter(MetricFilter.ALL)
		         .build(statsd);
		reporter.start(15, TimeUnit.SECONDS);
	}

	public MetricRegistry getRegistry() {
		return registry;
	}
	
	@Override
	public void markJob(){
		 registry.meter("articles.import.requests").mark();
	}
		
	public Timer getJobTimer() {
		return registry.timer("articles.import.timer");
	}

	@Override
	public Timer.Context startTimer() {
		return getJobTimer().time();		
	}

	@Override
	public void endTimer(Object c) {
		if(c!=null && c instanceof Timer.Context){
			((Timer.Context)c).stop();
		}			
	}

	@Override
	public void markSuccess() {
		registry.counter("articles.import.success").inc();		
	}

	@Override
	public void markFailure() {
		registry.counter("articles.import.fail").inc();		
	}

	@Override
	public void reportTotalArticlesReceived(int count) {
		registry.histogram("articles.import.total.received").update(count);		
	}

	@Override
	public void reportTotalArticlesUploaded(int count) {
		registry.histogram("articles.import.new.articles.uploaded").update(count);		
	}
	
}
