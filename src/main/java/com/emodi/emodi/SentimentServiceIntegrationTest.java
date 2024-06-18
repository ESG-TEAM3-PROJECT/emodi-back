package com.emodi.emodi;

import com.emodi.emodi.entity.Sentiment;
import com.emodi.emodi.service.SentimentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // 테스트 프로파일 사용
public class SentimentServiceIntegrationTest {

    @Autowired
    private SentimentService sentimentService;

    @Test
    public void testAnalyzeSentiment_withPositiveText() {
        // 테스트할 텍스트
        String content = "나는 참으로 행복합니다";

        // 감정 분석 호출
        Sentiment result = sentimentService.analyzeSentiment(content);

        // 결과 검증
        assertNotNull(result);
        System.out.println("Sentiment: " + result.getSentiment());
        System.out.println("Mood: " + result.getMood());

        // 기대되는 감정 분석 결과 확인
        assertEquals("positive", result.getSentiment().toLowerCase());
    }

    @Test
    public void testAnalyzeSentiment_withNegativeText() {
        String content = "나는 오늘 친구랑 싸움ㅠㅠ";
        Sentiment result = sentimentService.analyzeSentiment(content);
        assertNotNull(result);
        System.out.println("Sentiment: " + result.getSentiment());
        System.out.println("Mood: " + result.getMood());
        assertEquals("negative", result.getSentiment().toLowerCase());
    }

    @Test
    public void testAnalyzeSentiment_withNeutralText() {
        String content = "오늘 아무것도 하지 않음";
        Sentiment result = sentimentService.analyzeSentiment(content);
        assertNotNull(result);
        System.out.println("Sentiment: " + result.getSentiment());
        System.out.println("Mood: " + result.getMood());
        assertEquals("neutral", result.getSentiment().toLowerCase());
    }
}
