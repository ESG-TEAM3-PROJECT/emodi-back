package com.emodi.emodi.controller;

import com.emodi.emodi.jwt.JwtProvider;
import com.emodi.emodi.service.DeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class DiaryDeletionController {

    @Autowired
    private final DeleteService deleteService;
    private final JwtProvider jwtProvider;

    @DeleteMapping("/diaries/{diaryId}")
    public ResponseEntity<String> deleteDiary(@PathVariable Long diaryId, @CookieValue("jwt") String token) {
            Long userId = jwtProvider.verifyToken(token);
            deleteService.deleteDiary(userId);
            return ResponseEntity.ok("일기 삭제가 완료되었습니다.");
    }
}
