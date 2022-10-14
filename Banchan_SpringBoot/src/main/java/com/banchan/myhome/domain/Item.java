package com.banchan.myhome.domain;

import org.springframework.web.multipart.MultipartFile;

public class Item {

	private int id;				//상품번호
	private String name;		//상품이름
	private String seller;		//판매자
	private int price;			//가격
	private String image;		//이미지
	private String description;	//상세설명
	private String location;	//판매지역
	private String allergy;		//알러지재료
	private int readcount;		//조회수
	private String regdate;		//등록일
	private int status;			//판매여부
	
	private MultipartFile[] uploadfile;
	private String original;	//첨부될 파일의 이름
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate.substring(5,16);
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public MultipartFile[] getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(MultipartFile[] uploadfile) {
		this.uploadfile = uploadfile;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String bOARD_Original) {
		original = bOARD_Original;
	}	
	
	
//	private int 	ID;			//상품번호
//	private String  NAME;		//상품이름
//	private String  SELLER;		//판매자
//	private int  	PRICE;		//가격
//	private String  IMAGE;		//이미지
//	private String  DESCRIPTION;//상세설명
//	private String 	LOCATION;	//판매지역
//	private String 	ALLERGY;	//알러지재료
//	private int		READCOUNT;	//조회수
//	private String 	REGDATE;	//등록일
//	private String	STATUS;		//판매여부
//	
//	private MultipartFile uploadfile;
//	private String ORIGINAL;	//첨부될 파일의 이름
//	
//	
//	public int getID() {
//		return ID;
//	}
//	public void setID(int iD) {
//		ID = iD;
//	}
//	public String getNAME() {
//		return NAME;
//	}
//	public void setNAME(String nAME) {
//		NAME = nAME;
//	}
//	public String getSELLER() {
//		return SELLER;
//	}
//	public void setSELLER(String sELLER) {
//		SELLER = sELLER;
//	}
//	public int getPRICE() {
//		return PRICE;
//	}
//	public void setPRICE(int pRICE) {
//		PRICE = pRICE;
//	}
//	public String getIMAGE() {
//		return IMAGE;
//	}
//	public void setIMAGE(String iMAGE) {
//		IMAGE = iMAGE;
//	}
//	public String getDESCRIPTION() {
//		return DESCRIPTION;
//	}
//	public void setDESCRIPTION(String dESCRIPTION) {
//		DESCRIPTION = dESCRIPTION;
//	}
//	public String getLOCATION() {
//		return LOCATION;
//	}
//	public void setLOCATION(String lOCATION) {
//		LOCATION = lOCATION;
//	}
//	public String getALLERGY() {
//		return ALLERGY;
//	}
//	public void setALLERGY(String aLLERGY) {
//		ALLERGY = aLLERGY;
//	}
//	public int getREADCOUNT() {
//		return READCOUNT;
//	}
//	public void setREADCOUNT(int rEADCOUNT) {
//		READCOUNT = rEADCOUNT;
//	}
//	public String getREGDATE() {
//		return REGDATE;
//	}
//	public void setREGDATE(String rEGDATE) {
//		REGDATE = rEGDATE;
//	}
//	public String getSTATUS() {
//		return STATUS;
//	}
//	public void setSTATUS(String sTATUS) {
//		STATUS = sTATUS;
//	}
//	public MultipartFile getUploadfile() {
//		return uploadfile;
//	}
//	public void setUploadfile(MultipartFile uploadfile) {
//		this.uploadfile = uploadfile;
//	}
//	public String getORIGINAL() {
//		return ORIGINAL;
//	}
//	public void setORIGINAL(String bOARD_ORIGINAL) {
//		ORIGINAL = bOARD_ORIGINAL;
//	}	
	
}