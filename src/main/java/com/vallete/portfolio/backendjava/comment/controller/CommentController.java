package com.vallete.portfolio.backendjava.comment.controller;

import com.vallete.portfolio.backendjava.comment.dto.CommentDTO;
import com.vallete.portfolio.backendjava.comment.service.SaveCommentService;
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

    private final SaveCommentService saveCommentService;

    @PostMapping("/comment")
    public ResponseEntity saveComment(@RequestBody CommentDTO commentDTO) {
        return saveCommentService.saveComment(commentDTO);
    }
}
