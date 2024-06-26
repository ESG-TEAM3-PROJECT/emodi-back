package com.emodi.emodi.service;

import com.emodi.emodi.entity.Diary;
import com.emodi.emodi.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteService {

    @Autowired
    private DiaryRepository diaryRepository;

    public void deleteDiary(Long diaryId) {
        if (diaryRepository.existsById(diaryId)) {
            diaryRepository.deleteById(diaryId);
        } else {
            throw new IllegalArgumentException(diaryId + " 일기를 찾을 수 없습니다.");
        }
    }
}
