package com.banchan.myhome.service;

import java.util.List;

import com.banchan.myhome.domain.Notice;

public interface NoticeService {

	public int getListCount();

	public List<Notice> getBoardList(int page, int limit);

	public Notice getDetail(int num);
	
	public int setReadCountUpdate(int num);

	public void insertBoard(Notice board);

	public boolean isBoardWriter(int num, String pass);

	public int boardDelete(int num);

	public int boardModify(Notice boarddata);
	
}