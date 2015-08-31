package com.military.test.content.articles.metrics;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.military.content.articles.metrics.ArticlesImportMetrics;
import com.military.test.content.articles.TestArticlesImport;

public class TestMetrics extends TestArticlesImport {

	@Autowired
	private ArticlesImportMetrics metricsProvider;
	
	@Test
	public void testMetrics(){
		log.info("testing metrics...");
		metricsProvider.markJob();
	}
	
	
}
