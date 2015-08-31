package com.military.content.articles.metrics;

import com.codahale.metrics.Timer;

public interface ArticlesImportMetrics {

	public Timer.Context startTimer();
	public void endTimer(Object c);
	public void markSuccess();	
	public void markFailure();
	public void markJob();
	public void reportTotalArticlesReceived(int count);
	public void reportTotalArticlesUploaded(int count);
	
}
