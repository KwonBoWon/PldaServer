package me.bowon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.bowon.springbootdeveloper.domain.FeedbackPost;
import me.bowon.springbootdeveloper.domain.plda.FeedbacksEntity;
import me.bowon.springbootdeveloper.repository.FeedbacksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class FeedbackService {

    @Autowired
    private FeedbacksRepository feedbacksRepository;

    public void insertFeedback(FeedbackPost feedbackPost){
        FeedbacksEntity feedbacksEntity = new FeedbacksEntity();
        feedbacksEntity.setPromptId(feedbackPost.getPrompts_id());
        feedbacksEntity.setFeedback(feedbackPost.getFeedback());
        feedbacksEntity.setTitle(feedbackPost.getTitle());

        feedbacksRepository.save(feedbacksEntity);
    }
}
