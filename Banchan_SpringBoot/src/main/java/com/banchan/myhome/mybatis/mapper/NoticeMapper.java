package com.banchan.myhome.mybatis.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.banchan.myhome.domain.Notice;

@Mapper
public interface NoticeMapper {

	public int getListCount();

	public List<Notice> getBoardList(HashMap<String, Integer> map);

	public Notice getDetail(int num);

	public int setReadCountUpdate(int num);

	public void insertBoard(Notice board);

	public Notice isBoardWriter(HashMap<String, Object> map);

	public int boardDelete(int num);

	public int boardModify(Notice modifyboard);

}