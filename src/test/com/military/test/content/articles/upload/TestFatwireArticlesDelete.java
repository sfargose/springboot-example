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
public class TestFatwireArticlesDelete extends TestArticlesImport {

	@Autowired
	private FatwireArticlesImport articlesImport;
	
	@Value("${fatwire.delete.news.ini.file}")
	private String newsDeleteIniFile;
	
	@Value("${fatwire.delete.image.ini.file}")
	private String imageDeleteIniFile;
	
	//@Test
	public void testDeleteAssetsAndClean(){
		articlesImport.importFile(newsDataFolder+tempFolder, newsDeleteIniFile);
		articlesImport.importFile(imageConfigFolder+tempFolder, imageDeleteIniFile);
		
		articlesImport.deleteTempFiles(imageConfigFolder);
		articlesImport.deleteTempFiles(newsDataFolder);
		articlesImport.deleteTempFiles(imageFilesFolder);
	}
	
	//@Test
	public void testDeleteTempFiles(){
		articlesImport.deleteTempFiles(imageConfigFolder);
		articlesImport.deleteTempFiles(newsDataFolder);
		articlesImport.deleteTempFiles(imageFilesFolder);
	}
	
	@Test
	public void testDeleteImagesAndArticles(){
		articlesImport.importFile(newsDataFolder+tempFolder, newsDeleteIniFile);
		articlesImport.importFile(imageConfigFolder+tempFolder, imageDeleteIniFile);
	}
	
	
	//@Test
	public void testDeleteArticles(){
		articlesImport.importFile(newsDataFolder+tempFolder, newsDeleteIniFile);
	}
	
	//@Test
	public void testDeleteImages(){
		articlesImport.importFile(imageConfigFolder+tempFolder, imageDeleteIniFile);
	}
	
	
	public FatwireArticlesImport getArticlesImport() {
		return articlesImport;
	}

	public void setArticlesImport(FatwireArticlesImport articlesImport) {
		this.articlesImport = articlesImport;
	}

}
