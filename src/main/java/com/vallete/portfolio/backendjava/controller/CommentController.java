package com.vallete.portfolio.backendjava.controller;

import org.springframework.stereotype.Service;

import com.vallete.portfolio.backendjava.dto.CommentDTO;
import com.vallete.portfolio.backendjava.exception.BusinessException;
import com.vallete.portfolio.backendjava.model.Comment;
import com.vallete.portfolio.backendjava.repository.CommentRepository;

import javax.transaction.Transactional;

@Service
public class CommentController implements CommentControllerInterface {

	private final CommentRepository commentRepository;
	
	public CommentController(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	@Override
	@Transactional
	public Comment save(Comment comment) {
		validate(comment);
		
		return commentRepository.save(comment);
	}
	
	private void validate(Comment comment) {
		if(comment.getBody() == null || comment.getBody().trim() == "")
			throw new BusinessException("Some fields are required: Body");
	}
}
