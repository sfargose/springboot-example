package com.military.content.articles.source.newscred.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "content")
public class NewsCredArticleContentBean {

	private String description;
	private String title;
	private NewsCredArticleCategoryBean category;
	
	private List<NewsCredArticleImageBean> image_set=null;
	
	private NewsCredArticleSource source;
	private List<NewsCredArticleTopic> topic_set;
	private String created_at;
	private String modified_at;
	private String published_at;
	private List<NewsCredAuthor> author_set;
	private String guid;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlElement
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public NewsCredArticleCategoryBean getCategory() {
		return category;
	}
	public void setCategory(NewsCredArticleCategoryBean category) {
		this.category = category;
	}
	
	@XmlElementWrapper(name = "image_set")
    @XmlElement(name = "image")
	public List<NewsCredArticleImageBean> getImage_set() {
		return image_set;
	}
	public void setImage_set(List<NewsCredArticleImageBean> image_set) {
		this.image_set = image_set;
	}
	public NewsCredArticleSource getSource() {
		return source;
	}
	public void setSource(NewsCredArticleSource source) {
		this.source = source;
	}
	
	@XmlElementWrapper(name = "topic_set")
    @XmlElement(name = "topic")
	public List<NewsCredArticleTopic> getTopic_set() {
		return topic_set;
	}
	public void setTopic_set(List<NewsCredArticleTopic> topic_set) {
		this.topic_set = topic_set;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getModified_at() {
		return modified_at;
	}
	public void setModified_at(String modified_at) {
		this.modified_at = modified_at;
	}
	public String getPublished_at() {
		return published_at;
	}
	public void setPublished_at(String published_at) {
		this.published_at = published_at;
	}
	
	@XmlElementWrapper(name = "author_set")
    @XmlElement(name = "author")
	public List<NewsCredAuthor> getAuthor_set() {
		return author_set;
	}
	public void setAuthor_set(List<NewsCredAuthor> author_set) {
		this.author_set = author_set;
	}
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
}
