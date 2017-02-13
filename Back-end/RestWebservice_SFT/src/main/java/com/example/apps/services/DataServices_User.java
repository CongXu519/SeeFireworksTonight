package com.restbody.services;

import java.util.List;

import com.restbody.model.Status;
import com.restbody.model.User;

public interface DataServices_User {

	public List<User> getEntityList() throws Exception;
	
	public Status validate(User user) throws Exception;
}
