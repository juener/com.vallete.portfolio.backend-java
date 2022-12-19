package com.vallete.portfolio.backendjava.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vallete.portfolio.backendjava.controller.PostControllerInterface;
import com.vallete.portfolio.backendjava.controller.UserControllerInterface;
import com.vallete.portfolio.backendjava.dto.PostDTO;
import com.vallete.portfolio.backendjava.exception.BusinessException;
import com.vallete.portfolio.backendjava.model.Post;
import com.vallete.portfolio.backendjava.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PostAPI {

	private final PostControllerInterface postController;
	private final UserControllerInterface userController;
	
	@GetMapping("/post")
	public List<Post> getAllPosts() {
		return postController.findAll();
	}
	
	@PostMapping("/post")
	public ResponseEntity savePost(@RequestBody PostDTO postDTO) {
		try {
			Post post = convertDTO(postDTO);
			post = postController.save(post);
			return ResponseEntity.ok(post);
		}catch (BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	private Post convertDTO(PostDTO postDTO) {
		Post post = new Post();
		
		post.setId(postDTO.getId());
		post.setTitle(postDTO.getTitle());
		post.setBody(postDTO.getBody());
		post.setCreationDate(postDTO.getCreationDate());
		
		User user = userController.getUserById(postDTO.getUser());
		post.setUser(user);
			
		//creatioDate is default by postDTO
		return post;
	}
}
