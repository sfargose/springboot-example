package com.military.content.articles.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="document") 
public class FatwireImageBean extends FatwireAssetBean {

	private String imageRemoteFolder;
	private String imageRemoteSubFolder;
	private String localImageFileLocation;
	private String generateThumbnails;
	
	@XmlElement(name="_GROUP_Category") 
	public String getImageRemoteFolder() {
		return imageRemoteFolder;
	}
	public void setImageRemoteFolder(String imageFolder) {
		this.imageRemoteFolder = imageFolder;
	}
	
	@XmlElement(name="_GROUP_SubCategory") 
	public String getImageRemoteSubFolder() {
		return imageRemoteSubFolder;
	}
	public void setImageRemoteSubFolder(String imageSubFolder) {
		this.imageRemoteSubFolder = imageSubFolder;
	}
	
	@XmlElement(name="ImageFile") 
	public String getLocalImageFileLocation() {
		return localImageFileLocation;
	}
	public void setLocalImageFileLocation(String localImageFileLocation) {
		this.localImageFileLocation = localImageFileLocation;
	}
	
	@XmlElement(name="GenerateThumbnails") 
	public String getGenerateThumbnails() {
		return generateThumbnails;
	}
	public void setGenerateThumbnails(String generateThumbnails) {
		this.generateThumbnails = generateThumbnails;
	}
	
}
