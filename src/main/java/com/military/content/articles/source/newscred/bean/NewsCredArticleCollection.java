package com.military.content.articles.source.newscred.bean;

import java.util.List;

public class NewsCredArticleCollection {
	private int num_found;
	List<NewsCredArticleBean> collection_item_set;
	
	
	public int getNum_found() {
		return num_found;
	}
	public void setNum_found(int num_found) {
		this.num_found = num_found;
	}
	public List<NewsCredArticleBean> getCollection_item_set() {
		return collection_item_set;
	}
	public void setCollection_item_set(List<NewsCredArticleBean> collection_item_set) {
		this.collection_item_set = collection_item_set;
	}
}
