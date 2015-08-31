package com.military.test.content.articles.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.military.content.articles.fatwire.dao.FatwireDao;
import com.military.test.content.articles.TestArticlesImport;

public class TestFatwireDao extends TestArticlesImport{
	
	@Autowired
	private FatwireDao dao;
	
	@Test
	public void testIsArticlePresent() throws Exception{
		String externalId="52313087a5d32cb34b3dd73a695f6c45";
		log.info("is article present with guid "+externalId+":"+dao.isArticlePresent(externalId));
	}

}
