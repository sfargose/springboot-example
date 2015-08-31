package com.military.content.articles.batch;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.military.content.articles.bean.FatwireArticleBean;
import com.military.content.articles.bean.FatwireImageBean;
import com.military.content.articles.image.dao.ImageDao;
import com.military.content.articles.source.newscred.bean.NewsCredArticleContentBean;
import com.military.content.articles.source.newscred.bean.NewsCredArticleImageBean;
import com.military.content.articles.source.newscred.bean.NewsCredArticleTopic;
import com.military.content.articles.util.ArticleImportUtils;

@Component ("newscredFatwireConverter")
@Scope("step")
public class NewsCredFatwireConverter implements ItemProcessor<NewsCredArticleContentBean, FatwireArticleBean> {
	private Logger log = Logger.getLogger(this.getClass());

	@Value("${news.cred.date.format}")
	private String newsCredDateFormat; 

	@Value("${fatwire.date.format}")
	private String fatwireDateFormat;

	@Value("${fatwire.articles.folder}")
	private String fatwireArticlesFolder;

	@Value("${fatwire.breadcrumb.node}")
	private String fatwireBreadCrumbNode;


	@Autowired
	private ImageDao imageDao;	

	@Autowired
	private ArticleImportUtils utils;	

	@Value("#{jobParameters['tempFolder']}")
	private String tempFolder;	

	@Override
	public FatwireArticleBean process(NewsCredArticleContentBean item)
			throws Exception {

		return convertToFatwireBean(item);
	}

	public FatwireArticleBean convertToFatwireBean(NewsCredArticleContentBean newsCredBean){
		FatwireArticleBean fatwireBean=new FatwireArticleBean();
		fatwireBean.setAsset("maNews");
		fatwireBean.setType("NewsStory");
		if(newsCredBean.getGuid()!=null)
			fatwireBean.setExternalId(newsCredBean.getGuid());
		if(newsCredBean.getTitle()!=null){
			//headline
			fatwireBean.setHeadLine(utils.constructFatwireArticleHeadline(newsCredBean.getTitle()));

			//article url(fatwire asset name)
			fatwireBean.setItemName(utils.constructFatwireAssetName(newsCredBean.getTitle()));

		}

		if(newsCredBean.getSource()!=null){
			fatwireBean.setArticleSource(newsCredBean.getSource().getName());
		}
		try {
			fatwireBean.setBody(URLEncoder.encode(newsCredBean.getDescription(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {

		}
		fatwireBean.setGroupFolder(fatwireArticlesFolder);
		if(newsCredBean.getTopic_set()!=null){
			StringBuffer topics=new StringBuffer();
			//String topics=newsCredBean.getTopic_set().stream().map(NewsCredArticleTopic::getName).reduce((t,u) -> t+","+u).get();
			boolean firstItem=true;
			for(NewsCredArticleTopic topic:newsCredBean.getTopic_set()){
				if(!firstItem)
					topics.append(",");
				else
					firstItem=false;
				topics.append(topic.getName());
			}
			//log.info(topics);
			fatwireBean.setKeywords(topics.toString());
		}

		fatwireBean.setPublication("Military.com");
		fatwireBean.setBreadCrumbNode(fatwireBreadCrumbNode);	
		if(newsCredBean.getPublished_at()!=null){
			Date pubDate=getParsedNewsCredDate(newsCredBean.getPublished_at());
			if(pubDate!=null){
				DateFormat fatwireDateFormatter = new SimpleDateFormat(fatwireDateFormat);
				try {
					fatwireBean.setPubDate(URLEncoder.encode(fatwireDateFormatter.format(pubDate),"UTF-8"));
				} catch (UnsupportedEncodingException e) {

				}
			}

		}

		if(newsCredBean.getImage_set()!=null){

			List<FatwireImageBean> fatwireImageList=new ArrayList<FatwireImageBean>();
			String relatedImageName=null;
			for(int i=0;i<newsCredBean.getImage_set().size();i++){
				NewsCredArticleImageBean image=newsCredBean.getImage_set().get(i);
				if(image.getUrls()!=null){
					String imageName=utils.constructFatwireImageName(fatwireBean.getItemName())+i;
					if(i==0)//only get first item as related image
						relatedImageName=imageName;
					FatwireImageBean imageBean=getImageDao().getImage(image.getUrls().getLarge(), imageName, tempFolder);
					fatwireImageList.add(imageBean);
				}

			}
			fatwireBean.setRelatedImage(relatedImageName);
			fatwireBean.setImageAssets(fatwireImageList);					
		}

		return fatwireBean;
	}

	public Date getParsedNewsCredDate(String textDate){
		Date date=null;
		if(textDate!=null){
			try {

				DateFormat newsCredDateFormatter = new SimpleDateFormat(newsCredDateFormat);
				date=newsCredDateFormatter.parse(textDate);
			} catch (ParseException e) {
				log.error("couldn't parse newscred date  "+textDate+ " with format:"+newsCredDateFormat,e);
			}
		}
		return date;
	}

	public String getNewsCredDateFormat() {
		return newsCredDateFormat;
	}

	public void setNewsCredDateFormat(String newsCredDateFormat) {
		this.newsCredDateFormat = newsCredDateFormat;
	}

	public String getFatwireDateFormat() {
		return fatwireDateFormat;
	}

	public void setFatwireDateFormat(String fatwireDateFormat) {
		this.fatwireDateFormat = fatwireDateFormat;
	}

	public ImageDao getImageDao() {
		return imageDao;
	}

	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}

}
