package com.restbody.services;

import java.util.List;

import com.restbody.model.Site;

public interface DataServices_Site {
	
	public boolean addEntity(Site site) throws Exception;
	public List<Site> getEntityList() throws Exception;
	public Site getEntityByName(String name) throws Exception;
}
