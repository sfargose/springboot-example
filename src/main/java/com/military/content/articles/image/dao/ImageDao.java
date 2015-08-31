package com.military.content.articles.image.dao;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.military.content.articles.bean.FatwireImageBean;

public class ImageDao {

	private Logger log = Logger.getLogger(this.getClass());
	private String dataImageFilesFolder;
	private String dataImageConfigsFolder;
	
	
	public FatwireImageBean getImage(String imageUrl, String imageName, String tempFolder){
		FatwireImageBean fatwireBean=new FatwireImageBean();
		
		try {
			//Download and save image file on local disk
			String folder=getDataImageFilesFolder();
			if(tempFolder!=null && !"".equals(tempFolder))
				folder=folder+File.separator+tempFolder;
			String filePath=folder+File.separator+
					imageName.replaceAll("[ ]+", "-")+"."+getImageFileExtension(imageUrl);
			
			FileUtils.copyURLToFile(new URL(imageUrl), new File(filePath));
			//Create fatwire bean
			fatwireBean.setAsset("maImageAsset");
			fatwireBean.setType("Image");
			fatwireBean.setGenerateThumbnails("Yes");
			if(imageName!=null){
				fatwireBean.setItemName(imageName);
			}
			fatwireBean.setImageRemoteSubFolder("newscred");
			fatwireBean.setPublication("Military.com");
			//filePath=URLEncoder.encode(filePath,"UTF-8");
			fatwireBean.setLocalImageFileLocation(filePath);		
		} catch (MalformedURLException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		
		return fatwireBean;
	}
	
	public String getImageFileExtension(String imageUrl){
		String fileExtension="jpeg";
		try {
			URL url = new URL(imageUrl);
			HttpURLConnection connection = (HttpURLConnection)  url.openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
			String contentType = connection.getContentType();
			connection.disconnect();
			
			
			if(contentType!=null){
				fileExtension=contentType.substring(contentType.indexOf("/")+1).toLowerCase();
			}
		} catch (MalformedURLException e) {
			log.error(e);
		} catch (ProtocolException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}		
		return fileExtension;
	}


	public String getDataImageFilesFolder() {
		return dataImageFilesFolder;
	}


	public void setDataImageFilesFolder(String dataImageFilesFolder) {
		this.dataImageFilesFolder = dataImageFilesFolder;
	}


	public String getDataImageConfigsFolder() {
		return dataImageConfigsFolder;
	}


	public void setDataImageConfigsFolder(String dataImageConfigsFolder) {
		this.dataImageConfigsFolder = dataImageConfigsFolder;
	}

}
