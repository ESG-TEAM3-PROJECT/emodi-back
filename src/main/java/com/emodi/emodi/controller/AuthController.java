package com.emodi.emodi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emodi.emodi.entity.User;
import com.emodi.emodi.jwt.JwtProvider;
import com.emodi.emodi.service.AuthService;
import com.emodi.emodi.service.dto.SignupInfoRequest;
import com.emodi.emodi.service.dto.request.LoginInfoRequest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	private final JwtProvider jwtProvider;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody SignupInfoRequest request) {
		authService.signUp(request);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body("회원가입이 완료되었습니다.");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginInfoRequest request, HttpServletResponse response) {
		User user = authService.login(request);

		String jwt = jwtProvider.generateToken(user.getId());
		Cookie cookie = new Cookie("jwt", jwt);
		response.addCookie(cookie);

		return ResponseEntity.status(HttpStatus.OK)
			.body("로그인이 완료되었습니다.");
	}

	@GetMapping("/me")
	public Long getUserId(@CookieValue("jwt") String token) {
		return jwtProvider.verifyToken(token);
	}
}
