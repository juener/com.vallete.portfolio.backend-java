package com.vallete.portfolio.backendjava.comment.repository;

import com.vallete.portfolio.backendjava.comment.dto.CommentDTO;
import com.vallete.portfolio.backendjava.comment.model.CommentModel;
import com.vallete.portfolio.backendjava.comment.repository.jpa.CommentInterfaceRepositoryJPA;
import com.vallete.portfolio.backendjava.post.model.PostModel;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class SaveCommentRepository {

    @Autowired
    CommentInterfaceRepositoryJPA commentRepositoryJPA;

    public ResponseEntity saveComment(CommentDTO commentDTO, UserModel userModel, PostModel postModel){
        try{
            CommentModel commentModel = CommentModel.builder()
                    .user(userModel)
                    .post(postModel)
                    .id(commentDTO.getId())
                    .body(commentDTO.getBody())
                    .creationDate(commentDTO.getCreationDate())
                    .build();

            return new ResponseEntity(commentRepositoryJPA.save(commentModel), null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error creating commentary.", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
