package com.restbody.services;

import java.util.List;

import com.restbody.model.Comment;

public interface DataServices_Comment {

	public Comment getEntityById(long id) throws Exception;
	public List<Comment> getEntityByName(String name) throws Exception;// Site's name, every site has >=1 comments
	public boolean addEntity(Comment comment) throws Exception;
	public List<Comment> getEntityList() throws Exception;
	public boolean deleteEntity(long id) throws Exception;
}
