package com.banchan.myhome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banchan.myhome.domain.Buy;
import com.banchan.myhome.domain.Item;
import com.banchan.myhome.domain.Sell;
import com.banchan.myhome.domain.Wish;
import com.banchan.myhome.mybatis.mapper.MyMapper;

@Service
public class MyServiceImpl implements MyService {
	private static final Logger logger = LoggerFactory.getLogger(MyServiceImpl.class);
	
	@Autowired
	private MyMapper dao;
	
	@Override
	public List<Item> wish_list(String id, int page) {
		int startrow=1;
		int endrow=page*4;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.wish_list(map);
	}

	@Override
	public int getListCount(String id) {
		return dao.getListCount(id);
	}

	@Override
	public int wish_del(Wish wish) {
		return dao.wish_del(wish);
	}

	@Override
	public List<Item> sell_list(String id, int page) {
		int startrow=1;
		int endrow=page*4;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.sell_list(map);
	}

	@Override
	public int getSellListCount(String id) {
		return dao.getSellListCount(id);
	}

	@Override
	public int sell_del(Sell sell) {
		return dao.sell_del(sell);
	}

	@Override
	public int wish_del2(Sell sell) {
		return dao.wish_del2(sell);
	}

	@Override
	public int sellfn(int id) {
		return dao.sellfn(id);
	}

	@Override
	public List<Item> buy_list(String id, int page) {
		int startrow=1;
		int endrow=page*4;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.buy_list(map);
	}

	@Override
	public int getBuyListCount(String id) {
		return dao.getBuyListCount(id);
	}

	@Override
	public int buy_del(Buy buy) {
		return dao.buy_del(buy);
	}

	@Override
	public int wish_insert(Wish w) {
		return dao.wish_insert(w);
	}

	@Override
	public int wish_check(Wish w) {
		return dao.wish_check(w);
	}

	@Override
	public int wish_delete(Wish w) {
		return dao.wish_delete(w);
	}

	@Override
	public int buy_insert(Buy b) {
		return dao.buy_insert(b);
	}

	@Override
	public int buy_check(Buy b) {
		return dao.buy_check(b);
	}

	@Override
	public int buy_delete(Buy b) {
		return dao.buy_delete(b);
	}

}
