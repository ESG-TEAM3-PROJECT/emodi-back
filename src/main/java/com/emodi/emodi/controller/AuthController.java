package com.emodi.emodi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emodi.emodi.service.AuthService;
import com.emodi.emodi.service.dto.SignupInfoRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody SignupInfoRequest request) {
		authService.signUp(request);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body("회원가입이 완료되었습니다.");
	}
}
