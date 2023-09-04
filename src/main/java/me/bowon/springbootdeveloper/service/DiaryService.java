package me.bowon.springbootdeveloper.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.bowon.springbootdeveloper.domain.Diary;
import me.bowon.springbootdeveloper.dto.diary.AddDiaryRequest;
import me.bowon.springbootdeveloper.dto.diary.UpdateDiaryRequest;
import me.bowon.springbootdeveloper.repository.DiaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    // 일기 추가 메서드
    public Diary save(AddDiaryRequest request){
        return diaryRepository.save(request.toEntity());
    }

    public Diary findById(long id) {
        return diaryRepository.findById(id) // JPA findById()이용해서 ID를 받아 엔티티를 조회하고 IllegalArgumentException예외발생
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

    }
    public List<Diary> findAll(){
        return diaryRepository.findAll();
    }

    public void delete(long id){
        diaryRepository.deleteById(id);
    }

    @Transactional // 트랜잭션 메서드 매칭한 메서드를 하나의 트랜잭션으로 묶음
    public Diary update(long id, UpdateDiaryRequest request){
        Diary article = diaryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no found: " + id));

        article.update(request.getUserCode(), request.getContent());

        return article;
    }

}
