package com.banchan.myhome.service;

import java.util.List;

import com.banchan.myhome.domain.Item;

public interface MyService {
	// 찜 목록 리스트
	public List<Item> wish_list(String id, int page);

	public int getListCount(String id);

}
