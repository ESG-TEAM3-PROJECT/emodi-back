package com.emodi.emodi.service;

import com.emodi.emodi.entity.Diary;
import com.emodi.emodi.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository diaryRepository;

    public void deleteDiary(Long diaryId) {
        if (diaryRepository.existsById(diaryId)) {
            diaryRepository.deleteById(diaryId);
        } else {
            throw new IllegalArgumentException("Diary not found with id: " + diaryId);
        }
    }

    public Optional<Diary> getDiary(Long diaryId) {
        return diaryRepository.findById(diaryId);
    }
}
