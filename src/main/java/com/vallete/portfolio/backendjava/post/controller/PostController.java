package com.vallete.portfolio.backendjava.post.controller;

import com.vallete.portfolio.backendjava.post.dto.PostDTO;
import com.vallete.portfolio.backendjava.post.model.PostModel;
import com.vallete.portfolio.backendjava.post.service.interfaces.PostInterface;
import com.vallete.portfolio.backendjava.shared.exception.BusinessException;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.service.interfaces.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostInterface postInterface;
    private final UserInterface userInterface;

    @GetMapping
    public List<PostModel> getAllPosts() {
        return postInterface.findAll();
    }

    @PostMapping
    public ResponseEntity savePost(@RequestBody PostDTO postDTO) {
        UserModel userModel = userInterface.getUserById(postDTO.getUser());

        if(postDTO.getUser() == null)
            throw new BusinessException("User is required.");

        if(userModel == null)
            throw new BusinessException("User not found.");

        PostModel postModel = PostModel.builder()
                .user(userModel)
                .id(postDTO.getId())
                .title(postDTO.getTitle())
                .body(postDTO.getBody())
                .creationDate(postDTO.getCreationDate())
                .build();
        return ResponseEntity.ok(postInterface.save(postModel));
    }
}