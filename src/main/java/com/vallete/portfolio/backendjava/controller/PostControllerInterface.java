package com.vallete.portfolio.backendjava.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.vallete.portfolio.backendjava.model.Post;

public interface PostControllerInterface {

	List<Post> findAll();
	
	Post save(Post post);
	
//	Post update(Post post);
	
	Post getPostById(UUID idPost);
	
	void deleteByPostId(UUID idPost);
}
