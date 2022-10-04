package com.banchan.myhome.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banchan.myhome.controller.ItemController;
import com.banchan.myhome.domain.Item;
import com.banchan.myhome.mybatis.mapper.ItemMapper;

@Service
public class ItemServiceImpl implements ItemService {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	private ItemMapper dao;
	
	@Autowired
	public ItemServiceImpl(ItemMapper dao) {
		this.dao = dao;
	}

	@Override
	public void insertItem(Item item) {
		logger.info("Impl :" + item.getName());
		
		dao.insertItem(item);
	}


}