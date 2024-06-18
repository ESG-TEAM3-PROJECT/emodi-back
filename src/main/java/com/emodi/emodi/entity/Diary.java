package com.emodi.emodi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Diary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private User author;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sentiment_id")
	private Sentiment sentiment;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// content 반환 메소드
	public String getContent() {
		return content;
	}

	// sentiment 설정 메소드
	public void setSentiment(Sentiment sentiment) {
		this.sentiment = sentiment;
	}

	// createdAt 설정 메소드
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	// updatedAt 설정 메소드
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
