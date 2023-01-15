package com.vallete.portfolio.backendjava.user.repository;

import com.vallete.portfolio.backendjava.user.dto.UserDTO;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.repository.jpa.UserInterfaceRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterUserRepository {

    @Autowired
    UserInterfaceRepositoryJPA userRepository;

    public ResponseEntity saveUser(UserDTO userDTO){
        try{
            UserModel userModel = UserModel.builder()
                    .email(userDTO.getEmail())
                    .password(userDTO.getPassword())
                    .name(userDTO.getName())
                    .role("USER")
                    .build();
            return new ResponseEntity(userRepository.save(userModel), null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error to save the user.\n\n" + e, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
/*"select sum(T.value) from Transaction T join T.user U " +
                    "where U.id = :idUser and T.type = :type group by U"*/