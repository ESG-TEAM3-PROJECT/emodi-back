package com.emodi.emodi.controller;

import com.emodi.emodi.entity.Diary;
import com.emodi.emodi.jwt.JwtProvider;
import com.emodi.emodi.service.DiaryService;
import com.emodi.emodi.service.dto.request.WriteDiaryRequest;
import com.emodi.emodi.service.dto.response.WriteDiaryResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@Getter
@Setter
public class DiaryController {
	private final DiaryService diaryService;
	private final JwtProvider jwtProvider;

	@PostMapping("/diaries")
	public ResponseEntity<WriteDiaryResponse> writeDiary(
		@CookieValue("jwt") String token,
		@RequestBody WriteDiaryRequest request
	) {
		Long userId = jwtProvider.verifyToken(token);

		Diary diary = diaryService.writeDiary(userId, request);

		WriteDiaryResponse writeDiaryResponse = WriteDiaryResponse.toWriteDiaryResponse(
			"일기 작성이 완료되었습니다.",
			diary
		);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(writeDiaryResponse);
	}


	@PutMapping("/diaries/{diaryId}")
	public ResponseEntity<WriteDiaryResponse> updateDiary(
			@CookieValue("jwt") String token,
			@PathVariable Long diaryId,
			@RequestBody WriteDiaryRequest request
	) {
		Long userId = jwtProvider.verifyToken(token);

		Diary diary = diaryService.updateDiary(diaryId, request);

		WriteDiaryResponse writeDiaryResponse = WriteDiaryResponse.toWriteDiaryResponse(
				"일기 수정이 완료되었습니다.",
				diary
		);

		return ResponseEntity.status(HttpStatus.OK)
				.body(writeDiaryResponse);
	}
}
