package com.restbody.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.restbody.dao.DataDao_Site;
import com.restbody.model.Comment;
import com.restbody.model.Site;

public class DataServicesImpl_Site implements DataServices_Site{
	@Autowired
	DataDao_Site dataDao_Site;

	@Override
	public boolean addEntity(Site site) throws Exception {
		return dataDao_Site.addEntity(site);
	}
	
	@Override
	public List<Site> getEntityList() throws Exception {
		return dataDao_Site.getEntityList();
	}
	
	@Override
	public Site getEntityByName(String name) throws Exception {
		System.out.println("DataServicesImpl_Site started......");
		return dataDao_Site.getEntityByName(name);
	}

}
