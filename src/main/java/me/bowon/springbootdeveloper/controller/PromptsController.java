package me.bowon.springbootdeveloper.controller;

import me.bowon.springbootdeveloper.domain.plda.PromptsEntity;
import me.bowon.springbootdeveloper.repository.PromptsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/prompts")
public class PromptsController {

    @Autowired
    private PromptsRepository promptsRepository;
    @GetMapping(value = "/get")
    public List<PromptsEntity> getPrompts(){
        return promptsRepository.findTop3ByOrderByFeedbackDesc();
    }
}
