package com.emodi.emodi.service.dto.response;

import java.time.LocalDateTime;

import com.emodi.emodi.entity.Diary;

public record DairyDto(
	Long diaryId,
	Long authorId,
	String title,
	String content,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
	public static DairyDto toDiaryDto(Diary diary) {
		return new DairyDto(
			diary.getId(),
			diary.getAuthor().getId(),
			diary.getTitle(),
			diary.getContent(),
			diary.getCreatedAt(),
			diary.getUpdatedAt()
		);
	}
}
