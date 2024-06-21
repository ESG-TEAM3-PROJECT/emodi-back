package com.emodi.emodi.controller;

import com.emodi.emodi.jwt.JwtProvider;
import com.emodi.emodi.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diaries")
public class DiaryDeletionController {

    @Autowired
    private DiaryService diaryService;
    private JwtProvider jwtProvider;

    @DeleteMapping("/{diaryId}")
    public ResponseEntity<String> deleteDiary(@PathVariable Long diaryId, @CookieValue("jwt") String token) {
        try {
            Long userId = jwtProvider.verifyToken(token);
            validateToken(token);
            diaryService.deleteDiary(diaryId);
            return ResponseEntity.ok(new ResponseMessage("일기 삭제가 완료되었습니다."));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));
        }
    }

    private void validateToken(String token) {
        // Token validation logic here
    }

    public static class ResponseMessage {
        private String message;

        public ResponseMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
