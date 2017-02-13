package com.restbody.services;

import java.util.List;

import com.restbody.model.Favorite;

public interface DataServices_Favorite {
	
	public List<Favorite> getEntityByName(String username) throws Exception;
}
