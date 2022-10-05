package com.banchan.myhome.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banchan.myhome.domain.Notice;
import com.banchan.myhome.mybatis.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	private NoticeMapper dao;
	
	@Autowired
	public NoticeServiceImpl(NoticeMapper dao) {
		this.dao = dao;
	}
	
	@Override
	public int getListCount() {
		return dao.getListCount();
	}

	@Override
	public List<Notice> getBoardList(int page, int limit) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int startrow=(page-1)*limit+1;
		int endrow=startrow+limit-1;
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getBoardList(map);
	}

	@Override
	public Notice getDetail(int num) {
		if(setReadCountUpdate(num) != 1)
			return null;
		return dao.getDetail(num);
	}

	@Override
	public int setReadCountUpdate(int num) {
		return dao.setReadCountUpdate(num);
	}

	@Override
	public void insertBoard(Notice board) {
		dao.insertBoard(board);
	}

	@Override
	public boolean isBoardWriter(int num, String pass) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("pass", pass);
		Notice result = dao.isBoardWriter(map);
		if(result==null)
			return false;
		else
			return true;
	}

	@Override
	public int boardDelete(int num) {
		return dao.boardDelete(num);
	}

	@Override
	public int boardModify(Notice modifyboard) {
		return dao.boardModify(modifyboard);
	}


}