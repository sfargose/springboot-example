package com.military.content.articles.batch;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.military.content.articles.bean.FatwireArticleBean;
import com.military.content.articles.bean.FatwireAssetBean;
import com.military.content.articles.bean.FatwireImageBean;
import com.military.content.articles.fatwire.xmlconfig.XMLFileGenerator;
import com.military.content.articles.image.dao.ImageDao;
import com.military.content.articles.metrics.ArticlesImportMetrics;
import com.military.content.articles.upload.FatwireArticlesImport;
import com.military.content.articles.util.ArticleImportUtils;

@Component ("fatwireAssetWriter")
@Scope("step")
public class FatwireAssetWriter implements ItemWriter<FatwireArticleBean>{
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private XMLFileGenerator xmlFilegenerator;
	
	@Autowired
	private FatwireArticlesImport importer;
	
	@Autowired
	private ImageDao imageDao;
	
	@Value("${data.image.configs.folder}")
	private String imageConfigFolder;
	
	@Value("${data.image.files.folder}")
	private String imageDataFolder;
	
	@Value("${data.news.folder}")
	private String newsConfigFolder;
	
	@Value("${fatwire.news.ini.file}")
	private String newsIniFile;
	
	@Value("${fatwire.image.ini.file}")
	private String imageIniFile;	

	@Value("#{jobParameters['tempFolder']}")
	private String tempFolder;
	
	@Autowired
	private ArticlesImportMetrics metricsProvider;

	
	@Override
	public void write(List<? extends FatwireArticleBean> articles)
			throws Exception {
		if(articles!=null && articles.size()>0){
			for(FatwireArticleBean bean:articles){
				log.info(bean.getItemName());
				//check if any images are associated with article
				
				if(bean.getImageAssets()!=null){
					for(FatwireImageBean imageBean:bean.getImageAssets()){
						processFatwireAsset(imageBean, imageConfigFolder, getImageIniFile());
					}
				}				
				processFatwireAsset(bean, newsConfigFolder, getNewsIniFile());
			}
			metricsProvider.reportTotalArticlesUploaded(articles.size());
		}		
		
	}
	
	public void processFatwireAsset(FatwireAssetBean bean, String localConfigFolder, String iniFile){
		String filePath=ArticleImportUtils.constructFatwireConfigFileName(localConfigFolder,bean.getItemName(),tempFolder);
		//generate the config file from bean
		getXmlFilegenerator().writeToXml(bean, filePath);

		//import asset from config file into fatwire
		importer.importFile(filePath, iniFile);

	}

	public XMLFileGenerator getXmlFilegenerator() {
		return xmlFilegenerator;
	}

	public void setXmlFilegenerator(XMLFileGenerator xmlFilegenerator) {
		this.xmlFilegenerator = xmlFilegenerator;
	}

	public ImageDao getImageDao() {
		return imageDao;
	}

	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}

	public String getImageConfigFolder() {
		return imageConfigFolder;
	}

	public void setImageConfigFolder(String imageConfigFolder) {
		this.imageConfigFolder = imageConfigFolder;
	}

	public String getImageDataFolder() {
		return imageDataFolder;
	}

	public void setImageDataFolder(String imageDataFolder) {
		this.imageDataFolder = imageDataFolder;
	}

	public String getNewsConfigFolder() {
		return newsConfigFolder;
	}

	public void setNewsConfigFolder(String newsConfigFolder) {
		this.newsConfigFolder = newsConfigFolder;
	}

	public String getNewsIniFile() {
		return newsIniFile;
	}

	public void setNewsIniFile(String newsIniFile) {
		this.newsIniFile = newsIniFile;
	}

	public String getImageIniFile() {
		return imageIniFile;
	}

	public void setImageIniFile(String imageIniFile) {
		this.imageIniFile = imageIniFile;
	}

	public String getTempFolder() {
		return tempFolder;
	}

	public void setTempFolder(String tempFolder) {
		this.tempFolder = tempFolder;
	}

}
