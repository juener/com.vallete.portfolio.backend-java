package com.vallete.portfolio.backendjava.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.vallete.portfolio.backendjava.exception.BusinessException;
import com.vallete.portfolio.backendjava.model.Post;
import com.vallete.portfolio.backendjava.repository.PostRepository;

@Service
public class PostController implements PostControllerInterface {

	private PostRepository postRepository;

	public PostController(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public Post save(Post post) {
		validate(post);

		return postRepository.save(post);
	}

	private void validate(Post post) {
		if (post.getTitle() == null || post.getTitle().trim() == "") {
			throw new BusinessException("Some fields are required: Title, Body, User");
			// add more filters
		}
	}

	@Override
	public Post getPostById(UUID idPost) {
		return postRepository.getPostById(idPost);
	}
	
//	@Override
//	public Post update(Post post) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void deleteByPostId(UUID idPost) {
		postRepository.deleteById(idPost);
	}

}
