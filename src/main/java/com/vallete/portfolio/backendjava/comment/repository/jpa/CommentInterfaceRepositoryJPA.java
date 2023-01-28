package com.vallete.portfolio.backendjava.comment.repository.jpa;

import com.vallete.portfolio.backendjava.comment.model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentInterfaceRepositoryJPA extends JpaRepository<CommentModel, UUID> {

}
