package com.vallete.portfolio.backendjava.comment.controller;

import com.vallete.portfolio.backendjava.comment.dto.CommentDTO;
import com.vallete.portfolio.backendjava.comment.service.DeleteCommentService;
import com.vallete.portfolio.backendjava.comment.service.GetCommentsService;
import com.vallete.portfolio.backendjava.comment.service.SaveCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CommentController {

    private final GetCommentsService getCommentsService;
    private final SaveCommentService saveCommentService;
    private final DeleteCommentService deleteCommentService;

    @GetMapping("/comments")
    public ResponseEntity getComments(){
        return getCommentsService.getComments();
    }

    @PostMapping("/comment")
    public ResponseEntity saveComment(@RequestBody CommentDTO commentDTO) {
        return saveCommentService.saveComment(commentDTO);
    }

    @DeleteMapping("/comment")
    public ResponseEntity deleteComment(UUID idComment){
        return deleteCommentService.deleteComment(idComment);
    }
}
