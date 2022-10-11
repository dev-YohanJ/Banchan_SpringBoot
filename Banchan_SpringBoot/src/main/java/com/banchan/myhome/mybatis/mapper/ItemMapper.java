package com.banchan.myhome.mybatis.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.banchan.myhome.domain.Item;


@Mapper
public interface ItemMapper {
	
	//상품 등록
	public void insertItem(Item item) ;
	
	//상품 갯수 구하기
	public int getListCount();
	
	//상품 리스트 가져오기
	public List<Item> getItemList(HashMap<String, Integer> map);
	
	//글 내용 보기
	public Item getDetail(int num);
	
	//조회수 업데이트
	public int setReadCountUpdate(int num);
	
	//글 수정
	public int itemModify(Item modifyitem);
		
	//글 삭제
	public int itemDelete(Item board);

	
}