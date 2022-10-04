package com.banchan.myhome.controller;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banchan.myhome.domain.Notice;
import com.banchan.myhome.service.NoticeService;

@RestController
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	private NoticeService noticeService;
	
	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@GetMapping(value="/notice")
	public Map<String,Object> boardList(
			@RequestParam(value="page", defaultValue="1", required=false) int page,
			@RequestParam(value="limit", defaultValue="10", required=false) int limit) {
		
		int listcount = noticeService.getListCount();	//총 리스트 수를 받아옴
		
		int maxpage = (listcount + limit - 1) / limit;	//총 페이지 수
		
		//만약 maxpage가 2이고 page도 2인 경우
		//2페이지의 목록의 수가 한 개인 상태에서 남은 항목 한 개를 삭제한 경우
		//maxpage=1이 되고 page=2가 된다. 이런 경우 page는 maxpage로 수정
		if (page > maxpage)
			page = maxpage;

		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 ...)
		int startpage = ((page - 1) / 10) * 10 + 1;	
		
		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 ...)
		int endpage = startpage + 10 - 1;
		
		if (endpage > maxpage)
			endpage = maxpage;
		
		List<Notice> boardlist = noticeService.getBoardList(page, limit);	//리스트를 받아옴
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", page);
		map.put("maxpage", maxpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("listcount", listcount);
		map.put("boardlist", boardlist);
		map.put("limit", limit);
		return map;
	}
	
	@GetMapping("/notice/{num}")
	public Map<String, Object> Detail(@PathVariable int num) {
		Notice board = noticeService.getDetail(num);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board", board);	
		return map;
	}
}