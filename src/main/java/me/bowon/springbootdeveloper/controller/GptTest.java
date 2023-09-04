package me.bowon.springbootdeveloper.controller;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/gpt")
public class GptTest {

    @Value("${openai.api-key}")
    private String apiKey;

    @PostMapping("/post")
    public ResponseEntity<?> sendQuestion(@RequestBody String request){
        OpenAiService service = new OpenAiService(apiKey);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(request)
                .model("text-davinci-003")
                .echo(false)
                .maxTokens(100)
                .build();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
        return ResponseEntity.ok(service.createCompletion(completionRequest).getChoices());
    }

}

