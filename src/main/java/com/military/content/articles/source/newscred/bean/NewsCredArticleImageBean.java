package com.military.content.articles.source.newscred.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType (XmlAccessType.FIELD)
public class NewsCredArticleImageBean {

	private Integer width;
	private Integer height;
	private String created_at;
	private String published_at;
	private Source source;
	private URL urls;	
	
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getPublished_at() {
		return published_at;
	}

	public void setPublished_at(String published_at) {
		this.published_at = published_at;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public URL getUrls() {
		return urls;
	}

	public void setUrls(URL urls) {
		this.urls = urls;
	}

}
