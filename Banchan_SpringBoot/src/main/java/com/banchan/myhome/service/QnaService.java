package com.banchan.myhome.service;

import java.util.List;

import com.banchan.myhome.domain.Qna;

public interface QnaService {

	public int getListCount();

	public List<Qna> getBoardList(int page, int limit);

	public Qna getDetail(int num);
	
	public int setReadCountUpdate(int num);

	public void insertBoard(Qna board);

	public boolean isBoardWriter(int num, String pass);

	public int boardDelete(int num);

	public int boardModify(Qna boarddata);
	
}