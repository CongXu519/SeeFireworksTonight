package com.restbody.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.restbody.model.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.restbody.services.DataServices_Favorite;

@Controller
@RequestMapping("/favorite")
public class RestController_Favorite {

	@Autowired
	DataServices_Favorite dataServices;

	static final Logger logger = Logger.getLogger(RestController_Comment.class);

	@RequestMapping(value = "name/{name}", method = RequestMethod.GET)
	public @ResponseBody
	List<Favorite> getFavoritesByUser(@PathVariable("name") String name) {
		List<Favorite> favorites = null;
		try {
			favorites = dataServices.getEntityByName(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return favorites;
	}

}
