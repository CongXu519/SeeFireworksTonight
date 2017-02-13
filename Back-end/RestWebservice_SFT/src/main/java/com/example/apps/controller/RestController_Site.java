package com.restbody.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.restbody.model.Comment;
import com.restbody.model.Site;
import com.restbody.model.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restbody.services.DataServices_Site;

@Controller
@RequestMapping("/site")
public class RestController_Site {

	@Autowired
	DataServices_Site dataServices;

	static final Logger logger = Logger.getLogger(RestController_Site.class);
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status addSite(@RequestBody Site site) {
		try {
			dataServices.addEntity(site);
			return new Status(1, "site added Successfully !");
		} catch (Exception e) {
			// e.printStackTrace();
			return new Status(0, e.toString());
		}

	}
	
	@RequestMapping(value = "name/{name}", method = RequestMethod.GET)
	public @ResponseBody
	Site getSite(@PathVariable("name") String name) {
		System.out.println("rest_control_site started");
		Site site = null;
		try {
			site = dataServices.getEntityByName(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return site;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Site> getSites() {

		System.out.println("site list getting process start ...");
		List<Site> siteList = null;
		try {
			siteList = dataServices.getEntityList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return siteList;
	}
	
}
