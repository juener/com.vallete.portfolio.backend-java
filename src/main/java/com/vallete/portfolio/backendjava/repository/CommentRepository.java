package com.vallete.portfolio.backendjava.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vallete.portfolio.backendjava.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, UUID>{

}
