package com.emodi.emodi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emodi.emodi.entity.User;
import com.emodi.emodi.repository.UserRepository;
import com.emodi.emodi.service.dto.SignupInfoRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncryptor passwordEncryptor;

	public User signUp(SignupInfoRequest request) {
		if (userRepository.existsByUsername(request.username())) {
			throw new IllegalArgumentException("이미 존재하는 회원입니다.");
		}

		String encryptedPassword = passwordEncryptor.encryptPassword(request.password());
		User user = request.toUser(encryptedPassword);
		return userRepository.save(user);
	}
}
