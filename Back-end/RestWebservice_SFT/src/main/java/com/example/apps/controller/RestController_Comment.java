package com.restbody.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.restbody.model.Comment;
import com.restbody.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restbody.services.DataServices_Comment;

@Controller
@RequestMapping("/comment")
public class RestController_Comment {

	@Autowired
	DataServices_Comment dataServices;

	static final Logger logger = Logger.getLogger(RestController_Comment.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status addComment(@RequestBody Comment comment) {
		try {
			dataServices.addEntity(comment);
			return new Status(1, "comment added Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(0, e.toString());
		}

	}

	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Comment getComment(@PathVariable("id") long id) {
		Comment comment = null;
		try {
			comment = dataServices.getEntityById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return comment;
	}
	
	@RequestMapping(value = "name/{name}", method = RequestMethod.GET)
	public @ResponseBody
	List<Comment> getCommentsBySite(@PathVariable("name") String name) {
		List<Comment> comments = null;
		try {
			comments = dataServices.getEntityByName(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return comments;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Comment> getAllComments() {

		System.out.println("comment list getting process start ...");
		List<Comment> commentList = null;
		try {
			commentList = dataServices.getEntityList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		for(int i=0; i<commentList.size();i++){
			System.out.println(commentList.get(i));
		}
		
		return commentList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Status deleteComment(@PathVariable("id") long id) {

		try {
			dataServices.deleteEntity(id);
			return new Status(1, "comment deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}

	}
}
