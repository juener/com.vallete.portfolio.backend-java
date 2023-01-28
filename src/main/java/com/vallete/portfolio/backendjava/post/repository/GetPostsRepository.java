package com.vallete.portfolio.backendjava.post.repository;

import com.vallete.portfolio.backendjava.post.repository.jpa.PostInterfaceRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class GetPostsRepository {
    @Autowired
    PostInterfaceRepositoryJPA postRepository;

    public ResponseEntity getPosts(){
        try{
            return new ResponseEntity<>(postRepository.findAll(), null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error getting posts. \n\n" + e, null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
