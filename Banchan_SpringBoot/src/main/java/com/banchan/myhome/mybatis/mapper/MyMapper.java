package com.banchan.myhome.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.banchan.myhome.domain.Item;

@Mapper
public interface MyMapper {

	public List<Item> wish_list(Map<String, Object> map);

	public int getListCount(String id);

}
