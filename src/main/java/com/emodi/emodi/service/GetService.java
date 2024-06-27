package com.emodi.emodi.service;

import com.emodi.emodi.entity.Diary;
import com.emodi.emodi.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetService {

    private final DiaryRepository diaryRepository;

    public Optional<Diary> getDiary(Long diaryId, Long userId) {
        Optional<Diary> optionalDiary = diaryRepository.findById(diaryId);
        if (optionalDiary.isPresent() && optionalDiary.get().getAuthor().getId().equals(userId)) {
            return optionalDiary;
        } else {
            return Optional.empty();
        }
    }
}
