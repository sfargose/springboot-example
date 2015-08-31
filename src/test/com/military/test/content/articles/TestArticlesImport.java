package com.military.test.content.articles;

import java.io.File;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={
		"classpath:/databaseContext.xml",
		"classpath:/applicationContext.xml",
		"classpath:/batchConfig.xml"
		}) 
@RunWith(SpringJUnit4ClassRunner.class)  
public class TestArticlesImport extends TestCase {

	transient public Logger log = Logger.getLogger(this.getClass());
	
	@Value("${data.news.folder}")
	protected String newsDataFolder;
	
	@Value("${data.image.configs.folder}")
	protected String imageConfigFolder;
	
	@Value("${data.image.files.folder}")
	protected String imageFilesFolder;

	protected String tempFolder=File.separator+"2500";
	
	@Autowired
    protected ApplicationContext context;

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}
}
