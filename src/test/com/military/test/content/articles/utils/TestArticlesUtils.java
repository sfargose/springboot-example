package com.military.test.content.articles.utils;

import java.net.URLEncoder;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.military.content.articles.util.ArticleImportUtils;
import com.military.test.content.articles.TestArticlesImport;

public class TestArticlesUtils extends TestArticlesImport {

	@Autowired
	private ArticleImportUtils utils;
	
	private String title="EDITORIAL: A national security basic: Support troops, veterans";
	
	@Test
	public void testConstructAssetName(){
		assertEquals("EDITORIAL national security basic Support troops veterans", utils.constructFatwireAssetName(title));		
	}

	@Test
	public void testConstructHeadline()throws Exception{
		assertEquals(URLEncoder.encode("EDITORIAL a National Security Basic Support Troops, Veterans","UTF-8"), utils.constructFatwireArticleHeadline(title));	
	}

	public void testConstructImageName(){

	}
}
