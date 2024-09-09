package com.kyung.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/join-view")
	public String inputJoin(){
		return "user/join";
	}
	
	@GetMapping("login-view")
	public String inoutLogin(){
		return "user/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		// session 내의 내용을 삭제
		HttpSession session = request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		return "redirect:/user/login-view";
	}
	
	
}
