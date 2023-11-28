package me.bowon.springbootdeveloper.controller;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import me.bowon.springbootdeveloper.domain.Song;
import me.bowon.springbootdeveloper.domain.YoutubeDataList;
import me.bowon.springbootdeveloper.domain.plda.PromptsEntity;
import me.bowon.springbootdeveloper.repository.PromptsRepository;
import me.bowon.springbootdeveloper.service.GptService;
import me.bowon.springbootdeveloper.service.YoutubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/gpt")
public class GptController {

    @Value("${openai.api-key}")
    private String apiKey;
    private final GptService gptService;
    private final YoutubeService youtubeService;

    @Autowired
    PromptsRepository promptsRepository;

    private final String promptFormat =     // 프롬프트 양식
            "Desired Format: 1. song-singer";// \n Input: 다음 일기를 보고 노래 3가지를 추천해줘 \n";
    private String data;
    @PostMapping(value = "/post", headers = "PROMPT")
    public YoutubeDataList postQuestion(@RequestBody String request, @RequestHeader("PROMPT") int prompt_id) throws GeneralSecurityException, IOException {


        OpenAiService service = new OpenAiService(apiKey);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(promptFormat + getPrompt(prompt_id) + request) // 프롬프트 가져오기
                .model("text-davinci-003")
                .echo(false)
                .maxTokens(100)
                .temperature(0.7)
                .build();
        data = service.createCompletion(completionRequest).getChoices().toString();
        List<Song> songs = gptService.parseSong(data);
        System.out.println(songs);
        YoutubeDataList youtubeDataList = new YoutubeDataList(youtubeService.youtubeApi(songs));
        //youtubeDataList.getYoutubeDataList(youtubeService.youtubeApi(songs);

        return youtubeDataList;
    }
    public String getPrompt(long id){
        //PromptsEntity promptsEntity = promptsRepository.findById(id).orElse(null);
        PromptsEntity promptsEntity = promptsRepository.findById(id).orElse(null);
        assert promptsEntity != null;
        return promptsEntity.getPrompt();
    }
}

