package com.banchan.myhome.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.banchan.myhome.domain.Member;
import com.banchan.myhome.service.MemberService;

@RestController
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private MemberService memberservice;
	
	@Autowired
	public MemberController(MemberService memberservice) {
		this.memberservice = memberservice;
	}
	
	@PostMapping(value = "/members/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "logout successful";
	}
	
	@PostMapping(value="/members")
	public int loginProcess(@RequestBody Member member, HttpSession session) {
		String id = member.getId();
		String password = member.getPassword();
		int result = memberservice.isId(id, password);
		if (result == 1) {
			session.setAttribute("id", id);
		}
		return result;
	}
	
	@GetMapping(value="/members/idcheck")
	public int idcheck(String id) {
		return memberservice.isId(id);
	}
	
	@PostMapping(value="/members/new")
	public int joinProcess(@RequestBody Member member) {
		return memberservice.insert(member);
	}
	
	@GetMapping(value="/members/{id}")
	public Member member_info(@PathVariable String id) {
		logger.info("id=" + id);
		return memberservice.member_info(id);
	}
	
	//axios에서 put, patch, post 방식에서 매개변수로 object가 올 경우에는 @RequestBody를 붙인다.
	//그러나 FormData 형식으로 올 때는 안 붙인다.
	@PutMapping(value="/members")
	public int updateProcess(@RequestBody Member member) {
		return memberservice.update(member);
	}
	
	@DeleteMapping(value="/members/{id}")
	public int member_delete(@PathVariable String id) {
		return memberservice.delete(id);
	}
	
	@GetMapping(value = "/members")
	public Map<String,Object> memberList(
		@RequestParam(value="page", defaultValue="1", required=false) int page,
		@RequestParam(value="limit", defaultValue="3", required=false) int limit,
		@RequestParam(value="search_field", defaultValue="") String index,
		@RequestParam(value="search_word", defaultValue="") String search_word) {
		
		List<Member> list = null;
		int listcount = 0;
		
		//총 리스트 수를 받아온다.
		listcount = memberservice.getSearchListCount(index, search_word);
		
		int maxpage = (listcount + limit - 1) / limit;	//총 페이지 수

		if(page>maxpage)
			page=maxpage;
		
		list = memberservice.getSearchList(index, search_word, page, limit);
		
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 ...)
		int startpage = ((page - 1) / 10) * 10 + 1;	
		
		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 ...)
		int endpage = startpage + 10 - 1;
		
		if (endpage > maxpage)
			endpage = maxpage;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", page);
		map.put("maxpage", maxpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("listcount", listcount);
		map.put("memberlist", list);
		map.put("search_field", index);
		map.put("search_word", search_word);
		return map;
	}
}