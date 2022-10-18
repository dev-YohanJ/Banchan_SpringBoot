package com.banchan.myhome.service;

import java.util.HashMap;
import java.util.List;

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

	@Override
	public int getListCount() {
		return dao.getListCount();
	}

	@Override
	public List<Item> getItemList(int page, int limit) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int startrow=(page-1)*limit+1;
		int endrow=startrow+limit-1;
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getItemList(map);
	}

	@Override
	public Item getDetail(int num) {
		if(setReadCountUpdate(num)!=1)
			return null;
		return dao.getDetail(num);
	}
	
	@Override
	public int setReadCountUpdate(int num) {
		return dao.setReadCountUpdate(num);
	}


	@Override
	public int itemModify(Item modifyitem) {
		return dao.itemModify(modifyitem);
	}

	@Override
	public int itemDelete(int num) {
		int result=0;
		Item item = dao.getDetail(num);
		if(item != null) {
			result=dao.itemDelete(item);
		}
		return result;
	}

	@Override
	public void insertSell(Item item) {
		dao.insertSell(item);
	}

	@Override
	public Item memberDetail(String id) {
		return dao.memberDetail(id);
	}


}