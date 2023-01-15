package com.vallete.portfolio.backendjava.post.controller;

import com.vallete.portfolio.backendjava.post.dto.PostDTO;
import com.vallete.portfolio.backendjava.post.service.DeletePostService;
import com.vallete.portfolio.backendjava.post.service.GetPostsService;
import com.vallete.portfolio.backendjava.post.service.SavePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PostController {
    private final GetPostsService getPostService;
    private final SavePostService savePostService;
    private final DeletePostService deletePostService;

    @GetMapping("/post")
    public ResponseEntity getPosts() {
        return getPostService.getPosts();
    }

    @PostMapping("/post")
    public ResponseEntity savePost(@RequestBody PostDTO postDTO) {
        return savePostService.savePost(postDTO);
    }

    @DeleteMapping("/post/{idPost}")
    public ResponseEntity deletePost(@PathVariable("idPost") UUID idPost) {
        return deletePostService.deletePost(idPost);
    }

}