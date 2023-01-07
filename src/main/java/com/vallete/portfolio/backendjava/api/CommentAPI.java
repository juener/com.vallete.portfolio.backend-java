package com.vallete.portfolio.backendjava.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vallete.portfolio.backendjava.controller.CommentControllerInterface;
import com.vallete.portfolio.backendjava.controller.PostControllerInterface;
import com.vallete.portfolio.backendjava.controller.UserControllerInterface;
import com.vallete.portfolio.backendjava.dto.CommentDTO;
import com.vallete.portfolio.backendjava.exception.BusinessException;
import com.vallete.portfolio.backendjava.model.Comment;
import com.vallete.portfolio.backendjava.model.Post;
import com.vallete.portfolio.backendjava.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CommentAPI {

	private final CommentControllerInterface commentController;
	private final UserControllerInterface userController;
	private final PostControllerInterface postController;
	
	@PostMapping("/comment")
	public ResponseEntity save(@RequestBody CommentDTO commentDTO) {
		try {
			Comment comment = convertDTO(commentDTO);
			comment = commentController.save(comment);
			return ResponseEntity.ok(comment);
		}catch (BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	private Comment convertDTO(CommentDTO commentDTO) {
		Comment comment = new Comment();
		
		comment.setId(commentDTO.getId());
		comment.setBody(commentDTO.getBody());
		
		User user = userController.getUserById(commentDTO.getUser());
		comment.setUser(user);
		
		Post post = postController.getPostById(commentDTO.getPost());
		comment.setPost(post);
		
		return comment;
	}
}
