package com.vallete.portfolio.backendjava.comment.service;

import com.vallete.portfolio.backendjava.comment.repository.DeleteCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCommentService {
    private final DeleteCommentRepository deleteCommentRepository;

    public ResponseEntity deleteComment(UUID id){
        return deleteCommentRepository.deleteComment(id);
    }
}
