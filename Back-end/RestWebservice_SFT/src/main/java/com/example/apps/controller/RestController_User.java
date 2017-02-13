package com.restbody.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.restbody.model.Comment;
import com.restbody.model.Status;
import com.restbody.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restbody.services.DataServices_User;

@Controller
@RequestMapping("/user")
public class RestController_User {

	@Autowired
	DataServices_User dataServices;

	static final Logger logger = Logger.getLogger(RestController_User.class);

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<User> getUsers() {

		List<User> userList = null;
		try {
			userList = dataServices.getEntityList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList;
	}
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status validate(@RequestBody User user) {
		try {
			dataServices.validate(user);
			return new Status(1, "User validates successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(0, e.toString());
		}

	}

	
}
