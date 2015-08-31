package com.military.content.articles.upload;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import COM.FutureTense.XML.Post.XMLPost;
import COM.FutureTense.XML.Post.XMLPost.XMLPostException;

public class FatwireArticlesImport {
	private Logger log = Logger.getLogger(this.getClass());
	private XMLPost poster = new XMLPost();
	
	public void importFile(String filePath, String iniFile){
		log.info("importing from config"+filePath+" using "+iniFile);
		String args [] = {"-s"+filePath,"-c"+iniFile};
		try {
			poster.doIt(args);
			
		} catch (XMLPostException e) {
			log.error("Error importing config:"+filePath,e);
		}
	}
	
	public void deleteTempFiles(String folder){
		try {
			FileUtils.cleanDirectory(new File(folder));
		} catch (IOException e) {
			log.error("Error deleting all files in directory, "+folder, e);
		} 
	}

	public XMLPost getPoster() {
		return poster;
	}

	public void setPoster(XMLPost poster) {
		this.poster = poster;
	}
	
}
