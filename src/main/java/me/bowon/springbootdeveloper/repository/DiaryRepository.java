package me.bowon.springbootdeveloper.repository;

import me.bowon.springbootdeveloper.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

}
