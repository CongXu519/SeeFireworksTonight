package com.restbody.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.restbody.dao.DataDao_Favorite;
import com.restbody.model.Favorite;

public class DataServicesImpl_Favorite implements DataServices_Favorite{
	@Autowired
	DataDao_Favorite dataDao_Favorite;


	@Override
	public List<Favorite> getEntityByName(String username) throws Exception {
		return dataDao_Favorite.getEntityByName(username);
	}

}
