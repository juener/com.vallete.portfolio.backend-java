package com.vallete.portfolio.backendjava.comment.service;

import com.vallete.portfolio.backendjava.comment.repository.GetCommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCommentsService {
    private final GetCommentsRepository getCommentsRepository;

    public ResponseEntity getComments(){
        return new ResponseEntity(getCommentsRepository.getComments(), null, HttpStatus.OK);
    }
}
