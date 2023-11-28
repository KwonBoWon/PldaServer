package me.bowon.springbootdeveloper.repository;

import me.bowon.springbootdeveloper.domain.plda.PromptsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromptsRepository extends JpaRepository<PromptsEntity, Long> {
    List<PromptsEntity> findTop3ByOrderByFeedbackDesc();

    List<PromptsEntity> findTop4ByOrderByFeedbackDesc();

    PromptsEntity findByFeedback(int feedback);

}
