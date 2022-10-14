package com.banchan.myhome.service;

import java.util.List;

import com.banchan.myhome.domain.Item;
import com.banchan.myhome.domain.Sell;
import com.banchan.myhome.domain.Wish;

public interface MyService {
	// 찜 목록 리스트
	public List<Item> wish_list(String id, int page);

	public int getListCount(String id);

	public int wish_del(Wish wish);

	public List<Item> sell_list(String id, int page);

	public int getSellListCount(String id);

	public int sell_del(Sell sell);

	public int wish_del2(Sell sell);
	
}
