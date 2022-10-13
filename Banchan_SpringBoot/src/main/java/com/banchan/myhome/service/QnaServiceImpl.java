package com.banchan.myhome.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banchan.myhome.domain.Qna;
import com.banchan.myhome.mybatis.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {
	
	private QnaMapper dao;
	
	@Autowired
	public QnaServiceImpl(QnaMapper dao) {
		this.dao = dao;
	}
	
	@Override
	public int getListCount() {
		return dao.getListCount();
	}

	@Override
	public List<Qna> getBoardList(int page, int limit) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int startrow=(page-1)*limit+1;
		int endrow=startrow+limit-1;
		map.put("start", startrow);
		map.put("end", endrow);
		return dao.getBoardList(map);
	}

	@Override
	public Qna getDetail(int num) {
		if(setReadCountUpdate(num) != 1)
			return null;
		return dao.getDetail(num);
	}

	@Override
	public int setReadCountUpdate(int num) {
		return dao.setReadCountUpdate(num);
	}

	@Override
	public void insertBoard(Qna board) {
		dao.insertBoard(board);
	}

	@Override
	public boolean isBoardWriter(int num, String pass) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("pass", pass);
		Qna result = dao.isBoardWriter(map);
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
	public int boardModify(Qna modifyboard) {
		return dao.boardModify(modifyboard);
	}


}