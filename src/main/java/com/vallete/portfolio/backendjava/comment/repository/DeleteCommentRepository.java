package com.vallete.portfolio.backendjava.comment.repository;

import com.vallete.portfolio.backendjava.comment.repository.jpa.CommentInterfaceRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class DeleteCommentRepository {
    @Autowired
    CommentInterfaceRepositoryJPA commentRepositoryJPA;

    public ResponseEntity deleteComment(UUID id){
        try {
            commentRepositoryJPA.deleteById(id);
            return new ResponseEntity("The comment has been deleted.", null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error deleting the comment.", null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
