package com.banchan.myhome.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.banchan.myhome.domain.Item;
import com.banchan.myhome.domain.Wish;

@Mapper
public interface MyMapper {

	public List<Item> wish_list(Map<String, Object> map);

	public int getListCount(String id);

	public int wish_del(Wish wish);

}
