package com.vallete.portfolio.backendjava.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vallete.portfolio.backendjava.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);
	Optional<User> findById(UUID id);

	User getUserById(UUID id);

	User getUserByEmail(String email);
}
