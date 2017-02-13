package com.restbody.dao;

import java.util.List;

import com.restbody.model.User;

public interface DataDao_User {

	public List<User> getEntityList() throws Exception;
}
