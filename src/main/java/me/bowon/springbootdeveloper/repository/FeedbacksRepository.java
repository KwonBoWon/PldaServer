package me.bowon.springbootdeveloper.repository;

import me.bowon.springbootdeveloper.domain.plda.FeedbacksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbacksRepository extends JpaRepository<FeedbacksEntity, Long> {

}