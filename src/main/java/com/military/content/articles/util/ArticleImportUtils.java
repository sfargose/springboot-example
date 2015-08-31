package com.military.content.articles.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.StandardToStringStyle;

public class ArticleImportUtils {

	private String articleUrlFilterprepositions;
	
	private Map<String, String> articleUrlSanitizeMap;
	
	private Map<String, String> articleHeadlineSanitizeMap;	

	private int fatwireAssetNameLimit;
	
	private List<String> prepositionsList=new ArrayList<String>();;
	
	public void init(){
		if(articleUrlFilterprepositions!=null)
			for(String item:articleUrlFilterprepositions.split(",")){
				prepositionsList.add(item.toLowerCase());
			}			
	}
	
	public String constructFatwireAssetName(String text){		
		String assetName=limitName(sanitizeText(filterPrepositions(text), articleUrlSanitizeMap), fatwireAssetNameLimit);
		
		return assetName;
	}
	
	public String constructFatwireArticleHeadline(String text){
		String headline=limitName(sanitizeText(text, articleHeadlineSanitizeMap), fatwireAssetNameLimit);
		
		//capitalize non-preposition words
		StringBuffer buf=new StringBuffer("");
		for(String word:headline.split("[\\s]")){
			if(!prepositionsList.contains(word.toLowerCase()))
				word=WordUtils.capitalize(word);
			else 
				word=word.toLowerCase();
			if(!"".equals(word))
				buf.append(word+" ");
		}
		try {
			headline=URLEncoder.encode(buf.toString().trim(),"UTF-8");
		} catch (UnsupportedEncodingException e) {}
		
		return headline;
	}
	
	public String constructFatwireImageName(String name){
		String imageTagName=" image";
		int limit=fatwireAssetNameLimit-imageTagName.length();
		limit=limit-2; //leaving space for digits e.g. image[10]
		String itemName=limitName(name,limit)+imageTagName;
		
		return itemName;
	}
	
	public static String constructFatwireConfigFileName(String folder,String itemName, String tempFolder){
		String fileName=null;
		if(itemName!=null){
			if(tempFolder!=null && !"".equals(tempFolder))
				folder=folder+File.separator+tempFolder;
			fileName=folder+File.separator+itemName.toLowerCase().replaceAll("[ ]+", "-")+".xml";
		}
		return fileName;
	}
	
	
	public String filterPrepositions(String text){		
		StringBuffer buf=new StringBuffer("");
		if(text!=null){
			for(String word:text.split("[\\s,]")){					
				if(!"".equals(word) && !prepositionsList.contains(word.trim().toLowerCase())){
					buf.append(word.trim());					
					buf.append(" ");
				}					
			}			
		}			
		return buf.toString().trim();
	}
	
	public static String limitName(String name, int limit){
		String itemName=null;
		if(name!=null){
			itemName=name.trim();					
			if(itemName.length()>limit){
				StringBuffer buf=new StringBuffer();
				for(String word:itemName.split("\\s")){
					if(buf.length()+1+word.length()<=limit){
						if(buf.length()!=0)
							buf.append(" ");
						buf.append(word);
					}

				}
				itemName=buf.toString();
			}
		}
		return itemName;
	}
	
	public static String sanitizeText(final String text, Map<String, String> rules){
		String filteredText=text;
		if(rules!=null)
			for (Map.Entry<String, String> entry : rules.entrySet()){
				filteredText=filteredText.replaceAll(entry.getKey(), entry.getValue());			    
			}
			
		return filteredText;
	}
		
	public static String showForm(Object form){
		StandardToStringStyle style=new StandardToStringStyle();
		style.setFieldSeparator("\n");
		style.setFieldSeparatorAtStart(true);
		return ReflectionToStringBuilder.toString(form,style);
	}

	public String getArticleUrlFilterprepositions() {
		return articleUrlFilterprepositions;
	}

	public void setArticleUrlFilterprepositions(String articleUrlFilterprepositions) {
		this.articleUrlFilterprepositions = articleUrlFilterprepositions;
	}

	public Map<String, String> getArticleHeadlineSanitizeMap() {
		return articleHeadlineSanitizeMap;
	}

	public void setArticleHeadlineSanitizeMap(
			Map<String, String> articleHeadlineSanitizeMap) {
		this.articleHeadlineSanitizeMap = articleHeadlineSanitizeMap;
	}

	public int getFatwireAssetNameLimit() {
		return fatwireAssetNameLimit;
	}

	public void setFatwireAssetNameLimit(int fatwireAssetNameLimit) {
		this.fatwireAssetNameLimit = fatwireAssetNameLimit;
	}

	public Map<String, String> getArticleUrlSanitizeMap() {
		return articleUrlSanitizeMap;
	}

	public void setArticleUrlSanitizeMap(Map<String, String> articleUrlSanitizeMap) {
		this.articleUrlSanitizeMap = articleUrlSanitizeMap;
	}
	
}
