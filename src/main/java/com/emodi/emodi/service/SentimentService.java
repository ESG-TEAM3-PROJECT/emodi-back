package com.emodi.emodi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emodi.emodi.entity.Sentiment;
import com.emodi.emodi.repository.SentimentRepository;
import com.emodi.emodi.service.dto.request.WriteDiaryRequest;
import com.emodi.emodi.service.dto.response.SentimentResponse;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SentimentService {
	private final SentimentAnalyzer sentimentAnalyzer;
	private final SentimentRepository sentimentRepository;

	public Sentiment analyze(WriteDiaryRequest request) {
		SentimentResponse sentimentResponse = sentimentAnalyzer.analyze(request.content());
		SentimentResponse.Document document = sentimentResponse.getDocument();
		SentimentResponse.Confidence confidence = document.getConfidence();

		Sentiment sentiment = Sentiment.builder()
			.mood(document.getSentiment())
			.neutral(confidence.getNeutral())
			.positive(confidence.getPositive())
			.negative(confidence.getNegative())
			.build();

		return sentimentRepository.save(sentiment);
	}
}
