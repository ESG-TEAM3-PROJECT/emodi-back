package com.emodi.emodi.service.dto;

import com.emodi.emodi.entity.User;

public record SignupInfoRequest(
	String username,
	String password
) {
	public User toUser(String encryptedPassword) {
		return User.builder()
			.username(username)
			.password(encryptedPassword)
			.build();
	}
}
