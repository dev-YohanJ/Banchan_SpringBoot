package com.banchan.myhome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banchan.myhome.domain.Item;
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
	
	
}
