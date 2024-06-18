package com.emodi.emodi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
public class Sentiment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String mood;

	// 감정 분석 결과를 저장하는 필드 추가
	private String sentiment;

	// sentiment 설정 메소드
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	// mood 설정 메소드
	public void setMood(String mood) {
		this.mood = mood;
	}
}
