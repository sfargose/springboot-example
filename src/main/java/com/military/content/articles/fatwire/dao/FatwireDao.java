package com.military.content.articles.fatwire.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class FatwireDao extends JdbcDaoSupport{

	private String schemaName;
	
	public boolean isArticlePresent(String externalId){
		boolean isPresent=false;
		
		String query="SELECT count(1) from"
				+ " "+schemaName+".maNews nl, "
				+ " "+schemaName+".maNews_Mungo nlm, "
				+ " "+schemaName+".macontentattribute attr"
				+ " where nlm.cs_attrid=attr.id and nlm.CS_OWNERID=nl.id"
				+ " and lower(attr.name) ='externalcontentid' "
				+ " and lower(nlm.STRINGVALUE)=lower(?)";
		if(externalId!=null && !"".equals(externalId))
			isPresent= getJdbcTemplate().queryForObject(
					query, new Object[] { externalId }, Integer.class) != 0;

		return isPresent;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
}
