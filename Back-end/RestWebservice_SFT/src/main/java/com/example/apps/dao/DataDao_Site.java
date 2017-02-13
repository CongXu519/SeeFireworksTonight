package com.restbody.dao;

import java.util.List;

import com.restbody.model.Site;

public interface DataDao_Site {
	
	public List<Site> getEntityList() throws Exception;
	public boolean addEntity(Site site) throws Exception;
	public Site getEntityByName(String name) throws Exception;
}
