package com.vallete.portfolio.backendjava.comment.controller;

import com.vallete.portfolio.backendjava.comment.dto.CommentDTO;
import com.vallete.portfolio.backendjava.comment.model.CommentModel;
import com.vallete.portfolio.backendjava.comment.service.interfaces.CommentInterface;
import com.vallete.portfolio.backendjava.post.service.interfaces.PostInterface;
import com.vallete.portfolio.backendjava.post.model.PostModel;
import com.vallete.portfolio.backendjava.shared.exception.BusinessException;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.service.interfaces.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CommentController {

    private final CommentInterface commentInterface;
    private final UserInterface userInterface;
    private final PostInterface postInterface;

    @PostMapping("/comment")
    public ResponseEntity save(@RequestBody CommentDTO commentDTO) {
        UserModel userModel = userInterface.getUserById(commentDTO.getUser());
        PostModel postModel = postInterface.getPostById(commentDTO.getPost());

        if(commentDTO.getUser() == null || commentDTO.getPost() == null)
            throw new BusinessException("User and Post is required.");

        if(userModel == null)
            throw new BusinessException("User not found.");

        if(postModel == null)
            throw new BusinessException("Post not found.");

        CommentModel commentModel = CommentModel.builder()
                .user(userModel)
                .post(postModel)
                .id(commentDTO.getId())
                .body(commentDTO.getBody())
                .creationDate(commentDTO.getCreationDate())
                .build();
        return ResponseEntity.ok(commentInterface.save(commentModel));
    }
}
