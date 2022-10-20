package com.banchan.myhome.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class KakaologinController {
	@RequestMapping(value = "/login", method = {RequestMethod.GET})
	@ResponseBody
	public String main() {
		String url = "https://kauth.kakao.com/oauth/authorize?client_id=da289785112f84899419754fc94000ae&redirect_uri=http://localhost:8080/login/kakao&response_type=code";
		System.out.println("login 컨트롤러 접근");
		return url;
	}
	
	@RequestMapping(value="/login/kakao")
	public void kakaoCallback(@RequestParam String code) {
		System.out.println("kakao callback 컨트롤러 접근");
		System.out.println(code);
	}

	KakaoAPI kakaoApi = new KakaoAPI();
	
	@RequestMapping(value="/kakao_login/kakao")
	public RedirectView kakaoCallback(@RequestParam String code, HttpSession session) {
		System.out.println("kakao callback 컨트롤러 접근");
		System.out.println(code);
		
		RedirectView redirectView = new RedirectView();
		
		//1. 코드전달 
		String access_token = kakaoApi.getAccessToken(code);
		
		System.out.println("1. access_token : " + access_token);
		
		
		
		//2. 인증코드로 토큰 전달
		HashMap<String, Object> userInfo = kakaoApi.getUserInfo(access_token);
		
		System.out.println("2. login info : " + userInfo);
		System.out.println("2-1. login info : " + userInfo.toString());
		
		if(userInfo.get("email") != null) {
			session.setAttribute("userId", userInfo.get("email"));
			session.setAttribute("access_token", access_token);
		}
		
		redirectView.addStaticAttribute("email", userInfo.get("email"));
		redirectView.setUrl("http://localhost:8081");
			
		return redirectView;
	}
}
