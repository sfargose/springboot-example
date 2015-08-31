package com.military.content.articles.batch;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.military.content.articles.fatwire.dao.FatwireDao;
import com.military.content.articles.source.newscred.bean.NewsCredArticleContentBean;

@Component ("newscredDuplicateArticleFilter")
@Scope("step")
public class NewsCredDuplicateArticleFilter implements ItemProcessor<NewsCredArticleContentBean, NewsCredArticleContentBean> {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private FatwireDao fatwireDao;	

	@Override
	public NewsCredArticleContentBean process(NewsCredArticleContentBean item)
			throws Exception {
		if(fatwireDao.isArticlePresent(item.getGuid())){
			log.info(item.getTitle()+ " is already present..skipping");
			return null;
		}
		else{
			log.info("'"+item.getTitle()+"' not found in datatabse");
			return item;
		}
			
		
	}
	
	
	

}
