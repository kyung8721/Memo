package com.kyung.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyung.memo.post.domain.Post;
import com.kyung.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	private PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createMemo(
			@RequestParam("title") String title
			, @RequestParam("contents") String contents
			, HttpSession session){ // HttpServletRequest request 대신 이렇게 사용하면 간단하게 사용할 수 있음
		
		// int로 다운캐스팅
		int userId = (Integer)session.getAttribute("userId"); // 로그인할 때 정한 것
		
		Post post = postService.addPost(userId, title, contents);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(post != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		
		return resultMap;
		
	}
	
	
	
}
