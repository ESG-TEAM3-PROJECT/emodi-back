
package com.emodi.emodi.service;

import com.emodi.emodi.entity.Diary;
import com.emodi.emodi.entity.Sentiment;
import com.emodi.emodi.repository.DiaryRepository;
import com.emodi.emodi.repository.SentimentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final SentimentService sentimentService;
    private final SentimentRepository sentimentRepository;

    public DiaryService(DiaryRepository diaryRepository, SentimentService sentimentService, SentimentRepository sentimentRepository) {
        this.diaryRepository = diaryRepository;
        this.sentimentService = sentimentService;
        this.sentimentRepository = sentimentRepository;
    }

    public Diary saveDiary(Diary diary) {
        Sentiment sentiment = sentimentService.analyzeSentiment(diary.getContent());
        sentiment = sentimentRepository.save(sentiment);
        diary.setSentiment(sentiment);
        diary.setCreatedAt(LocalDateTime.now());
        diary.setUpdatedAt(LocalDateTime.now());

        return diaryRepository.save(diary);
    }
}
