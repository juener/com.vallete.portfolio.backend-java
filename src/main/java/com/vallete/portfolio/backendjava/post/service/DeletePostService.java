package com.vallete.portfolio.backendjava.post.service;

import com.vallete.portfolio.backendjava.post.repository.DeletePostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeletePostService {
    private final DeletePostRepository deletePostRepository;

    public ResponseEntity deletePost(UUID id){
        return deletePostRepository.deletePost(id);
    }
}
