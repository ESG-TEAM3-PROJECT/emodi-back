package com.emodi.emodi.service;

import com.emodi.emodi.entity.Diary;
import com.emodi.emodi.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetService {

    @Autowired
    private static DiaryRepository diaryRepository;

    public static Optional<Diary> getDiary(Long diaryId) {
        return diaryRepository.findById(diaryId);
    }
}
