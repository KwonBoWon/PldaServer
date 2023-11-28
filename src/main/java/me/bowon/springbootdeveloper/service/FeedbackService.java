package me.bowon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.bowon.springbootdeveloper.domain.FeedbackPost;
import me.bowon.springbootdeveloper.domain.plda.FeedbacksEntity;
import me.bowon.springbootdeveloper.domain.plda.PromptsEntity;
import me.bowon.springbootdeveloper.repository.FeedbacksRepository;
import me.bowon.springbootdeveloper.repository.PromptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class FeedbackService {

    @Autowired
    private FeedbacksRepository feedbacksRepository;
    @Autowired
    private PromptsRepository promptsRepository;

    public void insertFeedback(FeedbackPost feedbackPost){
        FeedbacksEntity feedbacksEntity = new FeedbacksEntity();
        feedbacksEntity.setPromptId(feedbackPost.getPrompts_id());
        feedbacksEntity.setFeedback(feedbackPost.getFeedback());
        feedbacksEntity.setTitle(feedbackPost.getTitle());

        feedbacksRepository.save(feedbacksEntity);

        findPromptFeedback((feedbackPost.getPrompts_id()), feedbackPost.getFeedback());
    }
    public void findPromptFeedback(long id, int feedbackdata){
        //YourEntity yourEntity = yourEntityRepository.findById(1L).orElse(null);
        PromptsEntity promptsEntity = promptsRepository.findById(id).orElse(null);
        promptsEntity.addFeedback(feedbackdata);
        promptsRepository.save(promptsEntity);
        System.out.println("@@@"+feedbackdata);
    }


}
