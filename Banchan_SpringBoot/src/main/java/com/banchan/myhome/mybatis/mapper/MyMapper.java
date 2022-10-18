package com.banchan.myhome.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.banchan.myhome.domain.Buy;
import com.banchan.myhome.domain.Item;
import com.banchan.myhome.domain.Sell;
import com.banchan.myhome.domain.Wish;

@Mapper
public interface MyMapper {

	public List<Item> wish_list(Map<String, Object> map);

	public int getListCount(String id);

	public int wish_del(Wish wish);

	public List<Item> sell_list(Map<String, Object> map);

	public int getSellListCount(String id);

	public int sell_del(Sell sell);

	public int wish_del2(Sell sell);

	public int sellfn(int id);

	public List<Item> buy_list(Map<String, Object> map);
	
	public int getBuyListCount(String id);

	public int buy_del(Buy buy);
	
}
