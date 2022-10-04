package com.banchan.myhome.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.banchan.myhome.domain.Item;


@Mapper
public interface ItemMapper {

	public void insertItem(Item item) ;
	

}