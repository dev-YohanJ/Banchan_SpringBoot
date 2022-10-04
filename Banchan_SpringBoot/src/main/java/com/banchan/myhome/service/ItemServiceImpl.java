package com.banchan.myhome.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.banchan.myhome.controller.ItemController;
import com.banchan.myhome.domain.Item;
import com.banchan.myhome.mybatis.mapper.ItemMapper;

@Service
public class ItemServiceImpl implements ItemService {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	private ItemMapper dao;

	@Override
	public void insertItem(Item item) {
		logger.info("1" + item.getName());
		logger.info(item.getDescription());
		logger.info(item.getAllergy());
		dao.insertItem(item);
	}


}