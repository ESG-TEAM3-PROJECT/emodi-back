package com.emodi.emodi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emodi.emodi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByUsername(String username);
}
