package com.restbody.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.restbody.dao.DataDao_User;
import com.restbody.model.Status;
import com.restbody.model.User;

public class DataServicesImpl_User implements DataServices_User{
	@Autowired
	DataDao_User dataDao_user;

	@Override
	public List<User> getEntityList() throws Exception {
		return dataDao_user.getEntityList();
	}

	@Override
	public Status validate(User user) throws Exception {
		Status status = new Status();
		List<User> users = dataDao_user.getEntityList();
		for(User usr : users){
			if(user.getUserName().equals(usr.getUserName())){
				if(user.getPassword().equals(usr.getPassword())){
					status.setCode(1);
					status.setMessage("User exists");
				}else{
					
				}
			}
			else{
				
			}
		}
		
		return status;
	}

	
}
