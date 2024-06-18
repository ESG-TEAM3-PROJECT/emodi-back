package com.emodi.emodi.controller;

import com.emodi.emodi.entity.Sentiment;
import com.emodi.emodi.service.SentimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/sentiment")
public class SentimentController {

    private final SentimentService sentimentService;

    @Autowired
    public SentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<Sentiment> analyzeSentiment(@RequestBody Map<String, String> request) {
        String content = request.get("content");
        if (content == null || content.trim().isEmpty()) {
            // 변경된 부분: Sentiment 객체 생성 시 기본 값으로 채움
            return ResponseEntity.badRequest().body(new Sentiment(null, "Invalid input", "No text provided"));
        }

        Sentiment sentiment = sentimentService.analyzeSentiment(content);
        return ResponseEntity.ok(sentiment);
    }
}
