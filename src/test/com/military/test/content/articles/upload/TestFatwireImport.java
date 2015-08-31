/**
 * 
 */
package com.military.test.content.articles.upload;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.military.content.articles.upload.FatwireArticlesImport;
import com.military.test.content.articles.TestArticlesImport;

/**
 * @author sfargose
 *
 */
public class TestFatwireImport extends TestArticlesImport {

	@Autowired
	private FatwireArticlesImport articlesImport;
	
	@Value("${fatwire.news.ini.file}")
	private String newsIniFile;
	
	@Value("${fatwire.image.ini.file}")
	private String imageIniFile;	
	
	@Test
	public void testNewsImport(){
		articlesImport.importFile(newsDataFolder+tempFolder, newsIniFile);
	}
	
	//@Test
	public void testImagesImport(){
		articlesImport.importFile(imageConfigFolder, imageIniFile);
	}
	
	public FatwireArticlesImport getArticlesImport() {
		return articlesImport;
	}

	public void setArticlesImport(FatwireArticlesImport articlesImport) {
		this.articlesImport = articlesImport;
	}

}
