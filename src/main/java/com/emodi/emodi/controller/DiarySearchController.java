package com.emodi.emodi.controller;

import com.emodi.emodi.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diaries")
public class DiarySearchController {

    @Autowired
    private DiaryService diaryService;

    @GetMapping("/{diaryId}")
    public ResponseEntity<String> getDiary(@PathVariable Long diaryId, @CookieValue("jwt") String token) {
        try {
            validateToken(token);
            return diaryService.getDiary(diaryId)
                    .map(diary -> ResponseEntity.ok(diary))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Diary not found with id: " + diaryId)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()));
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
