package com.banchan.myhome.service;

import java.util.List;
import com.banchan.myhome.domain.Member;

public interface MemberService {

	public int isId(String id, String password);
	
	public int insert(Member m);
	
	public int isId(String id);
	
	public Member member_info(String id);
	
	public int delete(String id);
	
	public int update(Member m);
	
	public List<Member> getSearchList(String index, String search_word, int page, int limit);
	
	public int getSearchListCount(String index, String search_word);

	public int isnick(String nick);

	public int Profile(Member memberdata);
	
	public int nick(Member m);

	public int intro(Member m);

	public List<Member> getMemberList(String id, int page);

	public int getListCount(String id);

	public int checkPassword(Member m);

	public int nickcheck2(Member m);

	public int member_update(Member m);

	public int isemail(String email);

	public int id_find(String name, String email);

	public String id_find2(String email);

	public int id_find3(String id, String email);

	public int pass_change(Member m);
}