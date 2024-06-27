package com.emodi.emodi.controller;

import com.emodi.emodi.jwt.JwtProvider;
import com.emodi.emodi.service.GetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/diaries")
public class DiaryGetController {

    private final GetService getService;
    private final JwtProvider jwtProvider;

    @GetMapping("/{diaryId}")
    public ResponseEntity<String> getDiary(@PathVariable Long diaryId, @CookieValue("jwt") String token) {
        Long userId = jwtProvider.verifyToken(token);
        return getService.getDiary(diaryId, userId)
                .map(diary -> ResponseEntity.ok(diary.toString())) // assuming diary.toString() returns a meaningful string representation
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(diaryId + " 일기를 찾을 수 없습니다."));
    }
}
