package com.kyung.memo.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kyung.memo.post.domain.Post;
import com.kyung.memo.post.repository.PostRepository;

@Service
public class PostService {
	
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public Post addPost(int userId, String title, String contents){
		
		Post post = Post.builder()
				.userId(userId)
				.title(title)
				.contents(contents)
				.build();
		
		Post result = postRepository.save(post);
		
		return result;
	}
	
	public List<Post> getPostList(int userId){
		return postRepository.findByUserIdOrderByIdDesc(userId);
	}
	
	public Post getPost(int id){
		Optional<Post> optionalPost = postRepository.findById(id);
		
		Post post = optionalPost.orElse(null);
		
		return post;
	}
}