package com.banchan.myhome.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banchan.myhome.domain.Item;
import com.banchan.myhome.service.MyService;


@RestController
public class MyController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyController.class);
	
	private MyService myservice;
	
	@Autowired
	public MyController(MyService myservice) {
		this.myservice=myservice;
	}
	
	@GetMapping(value="/wish")
	public Map<String, Object> wish_list(@RequestParam String id,
										 @RequestParam int page) {
		logger.info("id=" + id);
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Item> item = myservice.wish_list(id, page);
		int listcount = myservice.getListCount(id);
		
		map.put("item", item);
		map.put("listcount", listcount);
		
		return map;
	}
	
}
