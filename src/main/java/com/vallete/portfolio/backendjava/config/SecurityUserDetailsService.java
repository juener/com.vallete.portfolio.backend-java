package com.vallete.portfolio.backendjava.config;

import com.vallete.portfolio.backendjava.controller.UserController;
import com.vallete.portfolio.backendjava.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.vallete.portfolio.backendjava.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
    //private final UserController userController;

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<User> userModel = userRepository.findById(UUID.fromString(id));

        if (userModel == null)
            throw new UsernameNotFoundException("User not registered.");

        return org.springframework.security.core.userdetails.User.builder() //*.userdetails.User is different than *.model.User
                .username(userModel.get().getEmail())
                .password(userModel.get().getPassword())
                .roles("USER")
                .build();
    }
}
