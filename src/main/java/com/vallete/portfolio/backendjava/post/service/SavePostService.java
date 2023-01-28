package com.vallete.portfolio.backendjava.post.service;

import com.vallete.portfolio.backendjava.post.dto.PostDTO;
import com.vallete.portfolio.backendjava.post.repository.SavePostRepository;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.repository.jpa.UserInterfaceRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavePostService  {
    private final UserInterfaceRepositoryJPA userService;
    private final SavePostRepository savePostRepository;

    public ResponseEntity savePost(PostDTO postDTO) {
        UserModel userModel = userService.getUserById(postDTO.getUser());

        ResponseEntity validateResponse  = validate(postDTO, userModel);
        if(validateResponse != null)
            return validateResponse;

        return savePostRepository.savePost(postDTO, userModel);
    }

    private ResponseEntity validate(PostDTO postDTO, UserModel userModel) {
        if (postDTO.getTitle() == null || postDTO.getTitle().trim() == "")
            return new ResponseEntity<>("Some fields are required: Title, Body, User",null, HttpStatus.UNPROCESSABLE_ENTITY); // add more filters

        if(postDTO.getUser() == null)
            return new ResponseEntity<>("User is required.", null, HttpStatus.UNPROCESSABLE_ENTITY);

        if(userModel == null)
            return new ResponseEntity("User not found.", null, HttpStatus.UNPROCESSABLE_ENTITY);

        return null;
    }
}
