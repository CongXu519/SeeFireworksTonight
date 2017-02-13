package com.restbody.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.restbody.dao.DataDao_Comment;
import com.restbody.model.Comment;

public class DataServicesImpl_Comment implements DataServices_Comment{
	
	@Autowired
	DataDao_Comment dataDao_Comment;

	@Override
	public Comment getEntityById(long id) throws Exception {
		return dataDao_Comment.getEntityById(id);
	}
	
	@Override
	public List<Comment> getEntityByName(String name) throws Exception {
		return dataDao_Comment.getEntityByName(name);
	}

	@Override
	public boolean addEntity(Comment comment) throws Exception {
		return dataDao_Comment.addEntity(comment);
	}

	@Override
	public List<Comment> getEntityList() throws Exception {
		return dataDao_Comment.getEntityList();
	}

	@Override
	public boolean deleteEntity(long id) throws Exception {
		return dataDao_Comment.deleteEntity(id);
	}

}
