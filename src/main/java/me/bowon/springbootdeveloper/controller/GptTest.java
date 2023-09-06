package me.bowon.springbootdeveloper.controller;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import me.bowon.springbootdeveloper.domain.Song;
import me.bowon.springbootdeveloper.service.BlogService;
import me.bowon.springbootdeveloper.service.GptService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import me.bowon.springbootdeveloper.service.BlogService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/gpt")
public class GptTest {

    @Value("${openai.api-key}")
    private String apiKey;
    private final GptService gptService;
    private String data;
    @PostMapping("/post")
    public List<Song> sendQuestion(@RequestBody String request){
        OpenAiService service = new OpenAiService(apiKey);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(request)
                .model("text-davinci-003")
                .echo(false)
                .maxTokens(100)
                .temperature(0.7)
                .build();

        //service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
        data = service.createCompletion(completionRequest).getChoices().toString();
        //return ResponseEntity.ok(service.createCompletion(completionRequest));
        System.out.println(data);
        return gptService.parseSong(data);
    }

}

