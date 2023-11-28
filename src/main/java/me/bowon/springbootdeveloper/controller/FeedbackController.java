package me.bowon.springbootdeveloper.controller;


import lombok.RequiredArgsConstructor;
import me.bowon.springbootdeveloper.domain.FeedbackPost;
import me.bowon.springbootdeveloper.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    @PostMapping(value = "/post")
    public void postFeedback(@RequestBody List<FeedbackPost> feedbackPostList) throws GeneralSecurityException, IOException {
        for (FeedbackPost feedbackPost : feedbackPostList) {
            feedbackService.insertFeedback(feedbackPost);
        }

    }


}
