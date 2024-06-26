package com.emodi.emodi.controller;

import com.emodi.emodi.jwt.JwtProvider;
import com.emodi.emodi.service.GetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diaries")
public class DiarySearchController {

    @Autowired
    private GetService getService;
    private JwtProvider jwtProvider;

    @GetMapping("/{diaryId}")
    public ResponseEntity<String> getDiary(@PathVariable Long diaryId, @CookieValue("jwt") String token) {
            Long userId = jwtProvider.verifyToken(token);
            return GetService.getDiary(diaryId)
                    .map(diary -> ResponseEntity.ok(diary.toString())) // assuming diary.toString() returns a meaningful string representation
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(diaryId + " 일기를 찾을 수 없습니다."));
    }

}
