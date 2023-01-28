package com.vallete.portfolio.backendjava.comment.service;

import com.vallete.portfolio.backendjava.comment.dto.CommentDTO;
import com.vallete.portfolio.backendjava.comment.repository.SaveCommentRepository;
import com.vallete.portfolio.backendjava.post.model.PostModel;
import com.vallete.portfolio.backendjava.post.repository.jpa.PostInterfaceRepositoryJPA;
import com.vallete.portfolio.backendjava.shared.exception.BusinessException;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.repository.jpa.UserInterfaceRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveCommentService{
    private final SaveCommentRepository saveCommentRepository;
    private final UserInterfaceRepositoryJPA userRepositoryJPA;
    private final PostInterfaceRepositoryJPA postRepositoryJPA;

    public ResponseEntity saveComment(CommentDTO commentDTO) {
        validateComment(commentDTO);

        UserModel userModel = userRepositoryJPA.getUserById(commentDTO.getId());
        PostModel postModel = postRepositoryJPA.getPostById(commentDTO.getPost());

        return saveCommentRepository.saveComment(commentDTO, userModel, postModel);
    }

    private void validateComment(CommentDTO commentDTO) {
        if(commentDTO.getBody() == null || commentDTO.getBody().trim() == "")
            throw new BusinessException("Some fields are required: Body");
    }
}
