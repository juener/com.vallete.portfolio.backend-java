package com.vallete.portfolio.backendjava.post.service;

import com.vallete.portfolio.backendjava.post.repository.GetPostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPostsService {
    private final GetPostsRepository getPostsRepository;
    public ResponseEntity getPosts() {
        return getPostsRepository.getPosts();
    }
}
