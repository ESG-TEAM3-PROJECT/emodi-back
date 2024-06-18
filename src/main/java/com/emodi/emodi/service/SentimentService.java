
package com.emodi.emodi.service;

import com.emodi.emodi.entity.Sentiment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SentimentService {
    @Value("${api.key}")
    private String apiKey;

    @Value("${api.secret}")
    private String apiSecret;

    private final RestTemplate restTemplate;

    public SentimentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Sentiment analyzeSentiment(String content) {
        String url = "https://naveropenapi.apigw.ntruss.com/sentiment-analysis/v1/analyze";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-NCP-APIGW-API-KEY-ID", apiKey);
        headers.set("X-NCP-APIGW-API-KEY", apiSecret);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("content", content);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

        Map<String, Object> responseBody = response.getBody();
        Map<String, Object> document = (Map<String, Object>) responseBody.get("document");

        Sentiment sentiment = new Sentiment();
        sentiment.setSentiment((String) document.get("sentiment"));
        Map<String, Double> confidence = (Map<String, Double>) document.get("confidence");
        sentiment.setMood("neutral: " + confidence.get("neutral") + ", positive: " + confidence.get("positive") + ", negative: " + confidence.get("negative"));

        return sentiment;
    }
}
