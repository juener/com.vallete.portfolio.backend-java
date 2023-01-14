package com.vallete.portfolio.backendjava.config;

import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.repository.UserRepository;
import com.vallete.portfolio.backendjava.user.service.interfaces.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<UserModel> userModel = userRepository.findById(UUID.fromString(id));

        if (userModel == null)
            throw new UsernameNotFoundException("User not registered.");

        return org.springframework.security.core.userdetails.User.builder() //*.userdetails.User is different than *.model.User
                .username(userModel.get().getEmail())
                .password(userModel.get().getPassword())
                .roles("USER")
                .build();
    }
}
