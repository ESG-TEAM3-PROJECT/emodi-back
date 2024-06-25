package com.emodi.emodi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emodi.emodi.entity.Sentiment;

public interface SentimentRepository extends JpaRepository<Sentiment, Long> {
}
