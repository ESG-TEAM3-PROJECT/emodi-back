

package com.emodi.emodi.repository;

import com.emodi.emodi.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

package com.emodi.emodi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emodi.emodi.entity.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
