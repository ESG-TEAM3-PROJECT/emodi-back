package com.emodi.emodi.service.dto.response;

import com.emodi.emodi.entity.Sentiment;

public record SentimentDto(
	String mood,
	double neutral,
	double positive,
	double negative
) {
	public static SentimentDto toSentimentDto(Sentiment sentiment) {
		return new SentimentDto(
			sentiment.getMood(),
			sentiment.getNeutral(),
			sentiment.getPositive(),
			sentiment.getNegative()
		);
	}
}
