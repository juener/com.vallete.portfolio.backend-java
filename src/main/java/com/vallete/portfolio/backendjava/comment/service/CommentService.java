package com.vallete.portfolio.backendjava.comment.service;

import com.vallete.portfolio.backendjava.comment.model.CommentModel;
import com.vallete.portfolio.backendjava.comment.service.interfaces.CommentInterface;
import com.vallete.portfolio.backendjava.comment.repository.CommentRepository;
import com.vallete.portfolio.backendjava.shared.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentInterface {

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public CommentModel save(CommentModel comment) {
        validate(comment);

        return commentRepository.save(comment);
    }

    private void validate(CommentModel comment) {
        if(comment.getBody() == null || comment.getBody().trim() == "")
            throw new BusinessException("Some fields are required: Body");
    }
}
