package com.banchan.myhome.controller;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.banchan.myhome.domain.MailVo;
import com.banchan.myhome.domain.Member;
import com.banchan.myhome.service.MemberService;
import com.naver.myhome.task.SendMail;

@RestController
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private MemberService memberservice;
	private SendMail sendMail;
	
	@Autowired
	public MemberController(MemberService memberservice, SendMail sendMail) {
		this.memberservice = memberservice;
		this.sendMail = sendMail;
	}
	
	@GetMapping(value = "/email/naver")
	public int email_naver(String email) {
		MailVo vo = new MailVo();
		vo.setTo(email);
		Random r = new Random();
		int random = r.nextInt(100000000)+ 111111111;
		vo.setContent("오늘의반찬의 인증번호는 " + random + "입니다.");
		logger.info(vo.getContent());
		sendMail.sendMail(vo);
		
		return random;
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
	
	@GetMapping(value="/members/nickcheck")
	public int nickcheck(String nickname) {
		return memberservice.isnick(nickname);
	}
	
	@GetMapping(value="/members/emailcheck")
	public int emailcheck(String email) {
		return memberservice.isemail(email);
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
	
	@DeleteMapping(value="/secession/{id}")
	public int secession(@PathVariable String id, HttpSession session) {
		logger.info("id =" + id);
		
		int result = memberservice.delete(id);
				
		//삭제 처리 실패한 경우
		if (result == 0) {
			return -1;
		}
		session.invalidate();
		//삭제 처리 성공한 경우 - 글 목록 보기 요청을 전송하는 부분
		logger.info("탈퇴하기 성공");
		return 1;
	}
	
	@GetMapping(value = "/members")
	public Map<String,Object> memberList(
		@RequestParam(value="page", defaultValue="1", required=false) int page,
		@RequestParam(value="limit", defaultValue="10", required=false) int limit,
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
	
	
	//프로필 사진 변경
	@Value("${my.savefolder}")
	private String saveFolder;
	
	
	@PatchMapping(value = "/members")
	public String ProfileAction(
			Member memberdata,
			@RequestParam (value="check", defaultValue="", required=false) String check
			) throws Exception {
		
		String message="";
		
		MultipartFile uploadfile = memberdata.getUploadfile();	
		
		logger.info(saveFolder);
		File file = new File(saveFolder);
		if(!file.exists()) {
			if(file.mkdir()) {
				logger.info("폴더 생성");
			} else {
				logger.info("폴더 생성 실패");
			}
		} else {
			logger.info("폴더존재");
		}
		
		// 파일 변경한 경우
		if (uploadfile!=null && !uploadfile.isEmpty()) {
			logger.info("파일 변경되었습니다.");
				
			String fileName = uploadfile.getOriginalFilename(); // 원래 파일명
			memberdata.setPic_original(fileName);
				
			String fileDBName = fileDBName(fileName, saveFolder);
				
			// transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			uploadfile.transferTo(new File(saveFolder + "/" + fileDBName));
				
			// 바뀐 파일명으로 저장
			memberdata.setPicture(fileDBName);
		} else {
			logger.info("선택 파일 없습니다.");
			memberdata.setPicture(""); // ""로 초기화 합니다.
			memberdata.setPic_original(""); // ""로 초기화 합니다.
		} // else end
		
		// DAO에서 수정 메서드 호출하여 수정합니다.
		int result = memberservice.Profile(memberdata);
		// 수정에 실패한 경우
		if (result == 0) {
			message="fail";
		} else { //수정 성공의 경우
			logger.info("프로필 수정 완료");
			message = "success";
		}
		
		return message;
	}

	private String fileDBName(String fileName, String saveFolder) {
		// 새로운 폴더 이름 : 오늘 년+월+일
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR); // 오늘 년도 구합니다.
		int month = c.get(Calendar.MONTH) + 1; // 오늘 월 구합니다.
		int date = c.get(Calendar.DATE); // 오늘 일 구합니다.
		
		String homedir = saveFolder + "/" + year + "-" + month + "-" + date;
		logger.info(homedir);
		File path1 = new File(homedir);
		if (!(path1.exists())) {
			path1.mkdir(); // 새로운 폴더를 생성
		}
		
		// 난수를 구합니다,
		Random r = new Random();
		int random = r.nextInt(100000000);
		
		/****** 확장자 구하기 시작 *******/
		int index = fileName.lastIndexOf(".");
		// 문자열에서 특정 문자열의 위치 값(index)를 반환합니다.
		// indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
		// lastindexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
		// (파일명에 점에 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
		logger.info("index = " + index);
		
		String fileExtension = fileName.substring(index + 1);
		logger.info("fileExtension = " + fileExtension);
		/***** 확장자 구하기 끝 *****/
		
		// 새로운 파일명
		String refileName = "bbs" + year + month + date + random + "." + fileExtension;
		logger.info("refileName = " + refileName);
		
		// 오라클 디비에 저장될 파일 명
		String fileDBName = year+ "-" + month + "-" + date +"/" + refileName;
		//String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
		logger.info("fileDBName = " + fileDBName);
		return fileDBName;
	}
	
	// 닉네임 수정
	@PatchMapping(value = "/members/nick")
	public int nick(@RequestBody Member m) {
		return memberservice.nick(m);
	}
	// 자기소개 수정
	@PatchMapping(value = "/members/intro")
	public int intro(@RequestBody Member m) {
		return memberservice.intro(m);
	}
	
	// 찜 목록
	@GetMapping(value = "/members/wishList")
	public Map<String, Object> wishList
			(@RequestParam String id,
			@RequestParam int page) {
		
		List<Member> list = memberservice.getMemberList(id, page);
		int listcount = memberservice.getListCount(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("listcount", listcount);
		return map;
	}
	
	@PostMapping(value = "members/pwcheck")
	public int pwcheck(@RequestBody Member m) {
		
		int result = memberservice.checkPassword(m);
		logger.info("result= " + result);
		return result;
	}
	
	@GetMapping(value="members/nickcheck2")
	public int Nick_check2(Member m) {
		return memberservice.nickcheck2(m);
	}
	
	@PatchMapping(value = "members/update")
	public int member_update(@RequestBody Member m) {
		return memberservice.member_update(m);
	}
}