
package com.emodi.emodi.repository;

import com.emodi.emodi.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
