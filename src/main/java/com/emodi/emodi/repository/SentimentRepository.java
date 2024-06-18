
package com.emodi.emodi.repository;

import com.emodi.emodi.entity.Sentiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentimentRepository extends JpaRepository<Sentiment, Long> {
}
