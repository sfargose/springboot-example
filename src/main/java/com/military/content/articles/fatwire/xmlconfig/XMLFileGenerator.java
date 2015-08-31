package com.military.content.articles.fatwire.xmlconfig;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.military.content.articles.bean.FatwireAssetBean;

public class XMLFileGenerator {
	private Logger log = Logger.getLogger(this.getClass());
	private Jaxb2Marshaller marshaller;
	
	

	public void writeToXml(FatwireAssetBean bean, String fileName){
		if(StringUtils.isEmpty(bean.getItemName()))
			return;
		 
		try {
			
			FileWriter fw=new FileWriter(fileName);
			marshaller.marshal(bean, new StreamResult(fw));
			fw.close();
		}catch (XmlMappingException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
	}


	public Jaxb2Marshaller getMarshaller() {
		return marshaller;
	}


	public void setMarshaller(Jaxb2Marshaller marshaller) {
		this.marshaller = marshaller;
	}
}
