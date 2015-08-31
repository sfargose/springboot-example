package com.military.content.articles.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.military.content.articles.batch.ImportArticlesJob;
import com.military.content.articles.upload.FatwireArticlesImport;

@Component
public class ImportScheduler {
	private Logger log = Logger.getLogger(this.getClass());
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	private ImportArticlesJob job;
	
	@Autowired
	private FatwireArticlesImport articlesImport;
	
	@Value("${data.news.folder}")
	protected String newsDataFolder;
	
	@Value("${data.image.configs.folder}")
	protected String imageConfigFolder;
	
	@Value("${data.image.files.folder}")
	protected String imageFilesFolder;
	
	
    @Scheduled(initialDelay=10000, fixedRate = 5*60*1000)
    public void launchArticlesImportJob() {
    	log.info("Starting job at " + dateFormat.format(new Date()));
    	job.launchJob();
        log.info("Finished job at " + dateFormat.format(new Date()));
    }
    
    @Scheduled(fixedRate = 10*24*60*60*1000)
    public void cleanLeftOverTempFolders(){
    	log.info("Attempting to clean previous temp files..");
    	articlesImport.deleteTempFiles(imageConfigFolder);
		articlesImport.deleteTempFiles(newsDataFolder);
		articlesImport.deleteTempFiles(imageFilesFolder);
    }
}
