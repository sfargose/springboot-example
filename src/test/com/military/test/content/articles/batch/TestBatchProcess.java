package com.military.test.content.articles.batch;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.military.content.articles.batch.ImportArticlesJob;
import com.military.test.content.articles.TestArticlesImport;

public class TestBatchProcess extends TestArticlesImport {

	@Autowired
	private ImportArticlesJob batchProcessor;
	
	@Test
	public void testLaunchJob(){
		
		batchProcessor.launchJob();
	}

	public ImportArticlesJob getBatchProcessor() {
		return batchProcessor;
	}

	public void setBatchProcessor(ImportArticlesJob batchProcessor) {
		this.batchProcessor = batchProcessor;
	}



}
