package com.vallete.portfolio.backendjava.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vallete.portfolio.backendjava.model.entity.Login;

public interface LoginRepository extends JpaRepository<Login, UUID> {

	// QueryMethods
	// Optional<Login> findByEmail(String email);
	boolean existsByEmail(String email);

	Optional<Login> findByEmail(String email);

	Login getLoginById(UUID id);

}
