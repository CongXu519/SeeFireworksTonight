package com.restbody.dao;

import java.util.List;

import com.restbody.model.Favorite;
import com.restbody.model.User;

public interface DataDao_Favorite {

	public List<Favorite> getEntityByName(String username) throws Exception;
}
