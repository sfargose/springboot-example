package com.military.test.content.articles.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.military.content.articles.bean.FatwireArticleBean;
import com.military.content.articles.fatwire.xmlconfig.XMLFileGenerator;
import com.military.test.content.articles.TestArticlesImport;

public class TestXMLFileGenerator extends TestArticlesImport {

	@Autowired
	@Qualifier("xmlFileGenerator")
	private XMLFileGenerator xmlFileGenerator;
	
	@Value("${data.news.folder}")
	private String dataFolder;
	
	@Test
	public void testWriteToXML(){
		FatwireArticleBean bean=new FatwireArticleBean();
		bean.setAsset("maNews");
		bean.setType("NewsStory");
		bean.setItemName("Programmatic article"+(int)(Math.random()*1000));
		bean.setArticleSource("Military.com");
		bean.setBlurb("sample blurb");
		bean.setBody("blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah ");
		bean.setGroupFolder("Test Upload Folder");
		bean.setItemDescription("sample description 1");
		bean.setKeywords("electronics");
		
		
		xmlFileGenerator.writeToXml(bean, dataFolder);
		
	}
	

	public XMLFileGenerator getXmlFileGenerator() {
		return xmlFileGenerator;
	}


	public void setXmlFileGenerator(XMLFileGenerator xmlFileGenerator) {
		this.xmlFileGenerator = xmlFileGenerator;
	}


	public String getDataFolder() {
		return dataFolder;
	}


	public void setDataFolder(String dataFolder) {
		this.dataFolder = dataFolder;
	}

}
