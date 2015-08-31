package com.military.test.content.articles.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.military.content.articles.bean.FatwireImageBean;
import com.military.content.articles.image.dao.ImageDao;
import com.military.content.articles.util.ArticleImportUtils;
import com.military.test.content.articles.TestArticlesImport;

public class TestImageDao extends TestArticlesImport {
	
	@Autowired
	private ImageDao imageDao;
			
	@Test
	public void testGetData(){
		String imageUrl="http://images.military.com/media/news/people/kerry-jubeir-600.jpg";
		FatwireImageBean bean=imageDao.getImage(imageUrl, imageUrl.substring(imageUrl.lastIndexOf("/")+1),null);
		assertNotNull(bean);
		log.info(ArticleImportUtils.showForm(bean));
	}
	
}
