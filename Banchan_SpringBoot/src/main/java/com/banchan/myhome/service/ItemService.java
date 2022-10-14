package com.banchan.myhome.service;

import java.util.List;

import com.banchan.myhome.domain.Item;

public interface ItemService {

	// 글 등록하기
	public void insertItem(Item item);
	
	// 글의 갯수 구하기
	public int getListCount();
	
	// 글 목록 보기
	public List<Item> getItemList(int page, int limit);
	
	//글 내용 보기
	public Item getDetail(int num);
	
	//조회수 업데이트
	public int setReadCountUpdate(int num);
	
	// 글 수정
	public int itemModify(Item modifyitem);

	// 글 삭제
	public int itemDelete(int num);

	public void insertSell(Item item);
	
}
