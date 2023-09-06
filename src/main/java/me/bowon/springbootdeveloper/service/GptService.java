package me.bowon.springbootdeveloper.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.bowon.springbootdeveloper.domain.Song;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class GptService {
    public List<Song> parseSong(String data) {

        // 1-3.으로 시작하는 패턴
        Pattern pattern = Pattern.compile("^[1-3].*");
        String [] arr = data.split("\n");
        List<String> list = new ArrayList<>();
        List<Song> songs = new ArrayList<>();
        for (String string: arr){
            Matcher matcher = pattern.matcher(string);
            if(matcher.matches()){
                String [] tmp = string.split(",");
                tmp[0] = tmp[0].replaceAll("^[1-3].", ""); // 앞의 1-3. 삭제
                tmp[0] = tmp[0].replaceAll(" ", ""); // 공백 삭제
                list.add(tmp[0]);
            }
        }
        for (String string: list){
            // TODO:여기에 YOUTUBE 연결
            String [] tmp = string.split("-");
            Song song = new Song(tmp[0], tmp[1]);
            System.out.println("@" + tmp[0]+ "#" +tmp[1]);
            songs.add(song);
        }
        return songs;
    }


}

