package com.vallete.portfolio.backendjava.post.repository;

import com.vallete.portfolio.backendjava.post.repository.jpa.PostInterfaceRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class DeletePostRepository {
    @Autowired
    PostInterfaceRepositoryJPA postRepository;
    public ResponseEntity deletePost(UUID id){
        try{
            postRepository.deleteById(id);
            return new ResponseEntity("The post has been deleted.", null, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("Error to delete the post. \n\n" + e, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
