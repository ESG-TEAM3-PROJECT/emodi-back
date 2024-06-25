package com.emodi.emodi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emodi.emodi.jwt.JwtProvider;
import com.emodi.emodi.service.DiaryService;
import com.emodi.emodi.service.dto.request.WriteDiaryRequest;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class DiaryController {
	private final DiaryService diaryService;
	private final JwtProvider jwtProvider;

	@PostMapping("/diaries")
	public ResponseEntity<String> writeDiary(
		@CookieValue("jwt") String token,
		@RequestBody WriteDiaryRequest request
	) {
		Long userId = jwtProvider.verifyToken(token);

		diaryService.writeDiary(userId, request);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body("일기 작성이 완료되었습니다.");
	}
}
