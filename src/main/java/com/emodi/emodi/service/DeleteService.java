package com.emodi.emodi.service;

import com.emodi.emodi.entity.Diary;
import com.emodi.emodi.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteService {

    private final DiaryRepository diaryRepository;

    public void deleteDiary(Long diaryId, Long userId) {
        Optional<Diary> optionalDiary = diaryRepository.findById(diaryId);
        if (optionalDiary.isPresent()) {
            Diary diary = optionalDiary.get();
            if (diary.getAuthor().getId().equals(userId)) {
                diaryRepository.deleteById(diaryId);
            } else {
                throw new IllegalArgumentException("사용자 ID가 일기 작성자와 일치하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException(diaryId + " 일기를 찾을 수 없습니다.");
        }
    }
}
