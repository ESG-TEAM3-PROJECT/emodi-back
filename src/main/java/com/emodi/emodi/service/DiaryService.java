package com.emodi.emodi.service;

import com.emodi.emodi.entity.Diary;
import com.emodi.emodi.entity.Sentiment;
import com.emodi.emodi.entity.User;
import com.emodi.emodi.repository.DiaryRepository;
import com.emodi.emodi.repository.UserRepository;
import com.emodi.emodi.service.dto.request.WriteDiaryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {
	private final UserRepository userRepository;
	private final DiaryRepository diaryRepository;
	private final SentimentService sentimentService;

	public Diary writeDiary(Long userId, WriteDiaryRequest request) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

		Sentiment sentiment = sentimentService.analyze(request);

		Diary diary = request.toDiary(user, sentiment);

		return diaryRepository.save(diary);
	}

	public Diary updateDiary(Long diaryId, WriteDiaryRequest request) {
		Diary diary = diaryRepository.findById(diaryId)
				.orElseThrow(() -> new IllegalArgumentException("일기를 찾을 수 없습니다."));

		Sentiment sentiment = sentimentService.analyze(request);

		diary.setTitle(request.title());
		diary.setContent(request.content());
		diary.setDate(LocalDate.parse(request.date()));
		diary.setSentiment(sentiment);
		diary.setUpdatedAt(LocalDateTime.now());

		return diaryRepository.save(diary);
	}
}
