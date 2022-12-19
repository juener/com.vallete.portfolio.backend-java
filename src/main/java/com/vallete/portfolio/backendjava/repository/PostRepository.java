package com.vallete.portfolio.backendjava.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vallete.portfolio.backendjava.model.Post;

public interface PostRepository extends JpaRepository<Post, UUID>{

	Post getPostById(UUID id);
}
