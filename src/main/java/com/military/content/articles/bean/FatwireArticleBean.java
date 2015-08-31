package com.military.content.articles.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="document")  
public class FatwireArticleBean extends FatwireAssetBean {

	
	private String groupFolder;
	private String breadCrumbNode;
	private String itemDescription;
	private String pubDate;
	private String headLine;
	private String articleSource;
	private String blurb;
	private String teaser;
	private String keywords;
	private String body;
	private String displayTeaser="No";
	private String linkToDiscussionBoard="No";
	private String externalId;
	
	
	
	@XmlElement(name="_GROUP_Folder") 
	public String getGroupFolder() {
		return groupFolder;
	}
	public void setGroupFolder(String groupFolder) {
		this.groupFolder = groupFolder;
	}
	
	@XmlElement(name="BreadcrumbNode") 
	public String getBreadCrumbNode() {
		return breadCrumbNode;
	}
	public void setBreadCrumbNode(String breadCrumbNode) {
		this.breadCrumbNode = breadCrumbNode;
	}
		
	@XmlElement(name="_ITEMDESCRIPTION_") 
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = "<![CDATA["+itemDescription+"]]";
	}
	
	@XmlElement(name="PubDate") 
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	@XmlElement(name="Headline") 
	public String getHeadLine() {
		return headLine;
	}
	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}
	
	@XmlElement(name="ArticleSource") 
	public String getArticleSource() {
		return articleSource;
	}
	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource;
	}
	
	@XmlElement(name="Blurb") 
	public String getBlurb() {
		return blurb;
	}
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}
	
	@XmlElement(name="Teaser") 
	public String getTeaser() {
		return teaser;
	}
	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}
	
	@XmlElement(name="Keywords") 
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	@XmlElement(name="Body1") 
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;//"<![CDATA["+body+"]]>";
	}
	
	@XmlElement(name="DisplayTeaser") 
	public String getDisplayTeaser() {
		return displayTeaser;
	}
	public void setDisplayTeaser(String displayTeaser) {
		this.displayTeaser = displayTeaser;
	}
	
	@XmlElement(name="LinkToDiscussionBoard") 
	public String getLinkToDiscussionBoard() {
		return linkToDiscussionBoard;
	}
	public void setLinkToDiscussionBoard(String linkToDiscussionBoard) {
		this.linkToDiscussionBoard = linkToDiscussionBoard;
	}
	
	@XmlElement(name="ExternalContentId") 
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
}
