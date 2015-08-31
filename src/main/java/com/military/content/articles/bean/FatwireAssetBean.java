package com.military.content.articles.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class FatwireAssetBean {

	private String publication;
	private String asset;
	private String itemName;
	private String type;
	private String relatedImage;
	private List<FatwireImageBean> imageAssets;
	

	@XmlElement(name="publication") 
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	
	@XmlElement(name="_ASSET_") 
	public String getAsset() {
		return asset;
	}
	public void setAsset(String asset) {
		this.asset = asset;
	}
	
	@XmlElement(name="_ITEMNAME_") 
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@XmlElement(name="_TYPE_") 
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@XmlElement(name="RelatedImage") 
	public String getRelatedImage() {
		return relatedImage;
	}
	public void setRelatedImage(String relatedImage) {
		this.relatedImage = relatedImage;
	}
	
	@XmlTransient
	public List<FatwireImageBean> getImageAssets() {
		return imageAssets;
	}
	public void setImageAssets(List<FatwireImageBean> imageAssets) {
		this.imageAssets = imageAssets;
	}
}
