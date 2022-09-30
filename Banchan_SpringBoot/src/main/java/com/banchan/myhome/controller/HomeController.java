package com.banchan.myhome.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping(value="/getSession")
	@ResponseBody
	public String getSession(HttpSession session) {
		String id = (String) session.getAttribute("id");
		logger.info("[getSession]id=" + id);
		return id;
	}
}