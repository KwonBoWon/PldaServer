package me.bowon.springbootdeveloper.controller;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import me.bowon.springbootdeveloper.domain.Song;
import me.bowon.springbootdeveloper.domain.YoutubeData;
import me.bowon.springbootdeveloper.domain.YoutubeDataList;
import me.bowon.springbootdeveloper.service.BlogService;
import me.bowon.springbootdeveloper.service.GptService;
import me.bowon.springbootdeveloper.service.YoutubeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import me.bowon.springbootdeveloper.service.BlogService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/gpt")
public class GptTest {

    @Value("${openai.api-key}")
    private String apiKey;
    private final GptService gptService;
    private final YoutubeService youtubeService;

    private final String promptFormat =     // 프롬프트 양식
            "Desired Format: 1. song-singer, \n Input: 다음 일기를 보고 노래 3가지를 추천해줘 \n";
    private String data;
    @PostMapping("/post")
    public YoutubeDataList sendQuestion(@RequestBody String request) throws GeneralSecurityException, IOException {
        OpenAiService service = new OpenAiService(apiKey);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(promptFormat + request)
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

}

