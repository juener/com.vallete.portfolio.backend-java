package com.vallete.portfolio.backendjava.post.repository;

import com.vallete.portfolio.backendjava.post.dto.PostDTO;
import com.vallete.portfolio.backendjava.post.model.PostModel;
import com.vallete.portfolio.backendjava.post.repository.jpa.PostInterfaceRepositoryJPA;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class SavePostRepository {
    @Autowired
    PostInterfaceRepositoryJPA postRepository;

    public ResponseEntity savePost(PostDTO postDTO, UserModel userModel) {
        try {
            PostModel postModel = PostModel.builder()
                    .user(userModel)
                    .id(postDTO.getId())
                    .title(postDTO.getTitle())
                    .body(postDTO.getBody())
                    .creationDate(postDTO.getCreationDate())
                    .build();

            return new ResponseEntity<>(postRepository.save(postModel), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving the post.\n\n" + e,null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
