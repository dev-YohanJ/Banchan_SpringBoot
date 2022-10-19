package com.banchan.myhome.domain;

public class Wish {
	private int id;
	private int[] item_id;
	private int item_id2;
	private String member_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItem_id2() {
		return item_id2;
	}
	public void setItem_id2(int item_id2) {
		this.item_id2 = item_id2;
	}
	public int[] getItem_id() {
		return item_id;
	}
	public void setItem_id(int[] item_id) {
		this.item_id = item_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
}
