package com.banchan.myhome.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.banchan.myhome.domain.Member;

@Mapper
public interface MemberMapper {
	public Member isId(String id);
	
	public int insert(Member m);
	
	public int update(Member m);

	public int delete(String id);

	public int getSearchListCount(Map<String, Object> map);

	public List<Member> getSearchList(Map<String, Object> map);

	public Member isnick(String nick);

	public int Profile(Member member);

	public int nick(Member m);

	public int intro(Member m);

	public int getListCount(String id);

	public int checkPassword(Member m);

	public int nickcheck2(Member m);

	public int member_update(Member m);

	public Member isemail(String email);

	public int id_find(String name, String email);

	public Member id_find2(String email);

	public int id_find3(String id, String email);

	public int pass_change(Member m);
}