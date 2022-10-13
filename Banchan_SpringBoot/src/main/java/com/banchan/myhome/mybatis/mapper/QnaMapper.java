package com.banchan.myhome.mybatis.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.banchan.myhome.domain.Qna;

@Mapper
public interface QnaMapper {

	public int getListCount();

	public List<Qna> getBoardList(HashMap<String, Integer> map);

	public Qna getDetail(int num);

	public int setReadCountUpdate(int num);

	public void insertBoard(Qna board);

	public Qna isBoardWriter(HashMap<String, Object> map);

	public int boardDelete(int num);

	public int boardModify(Qna modifyboard);

}