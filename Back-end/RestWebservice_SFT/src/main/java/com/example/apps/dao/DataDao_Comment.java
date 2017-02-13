package com.restbody.dao;

import java.util.List;

import com.restbody.model.Comment;
/**
 * Add, Delete, Update, Get
 * @author xu
 *
 */
public interface DataDao_Comment {

	public Comment getEntityById(long id) throws Exception;
	public List<Comment> getEntityByName(String name) throws Exception;// Site's name, every site has >=1 comments
	public boolean addEntity(Comment comment) throws Exception;
	public List<Comment> getEntityList() throws Exception;
	public boolean deleteEntity(long id) throws Exception;
	//public boolean updateEntity(long id) throws Exception;
}
