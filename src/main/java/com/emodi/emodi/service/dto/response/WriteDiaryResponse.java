package com.emodi.emodi.service.dto.response;

import com.emodi.emodi.entity.Diary;

public record WriteDiaryResponse(
	String message,
	DairyDto dairyDto,
	SentimentDto sentimentDto
) {
	public static WriteDiaryResponse toWriteDiaryResponse(String message, Diary dairy) {
		return new WriteDiaryResponse(
			message,
			DairyDto.toDiaryDto(dairy),
			SentimentDto.toSentimentDto(dairy.getSentiment())
		);
	}
}
