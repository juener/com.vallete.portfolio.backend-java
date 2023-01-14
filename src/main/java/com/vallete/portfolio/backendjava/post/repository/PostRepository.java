package com.vallete.portfolio.backendjava.post.repository;

import com.vallete.portfolio.backendjava.post.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostModel, UUID> {

    PostModel getPostById(UUID id);
}
