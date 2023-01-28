package com.vallete.portfolio.backendjava.comment.repository;

import com.vallete.portfolio.backendjava.comment.repository.jpa.CommentInterfaceRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class GetCommentsRepository {
    @Autowired
    CommentInterfaceRepositoryJPA commentRepositoryJPA;

    public ResponseEntity getComments(){
        try{
            return new ResponseEntity(commentRepositoryJPA.findAll(), null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error getting comments.", null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
