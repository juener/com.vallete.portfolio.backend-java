package com.vallete.portfolio.backendjava.comment.repository;

import com.vallete.portfolio.backendjava.comment.model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentModel, UUID> {

}
