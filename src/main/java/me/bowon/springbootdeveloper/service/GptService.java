package me.bowon.springbootdeveloper.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
    public List<String> parseSong(String data) {
        // 노래 제목과 가수를 추출하는 정규 표현식 패턴
        Pattern pattern = Pattern.compile("[0-9]+\\.\\s+([^\\n]+) - ([^,]+)");

        Matcher matcher = pattern.matcher(data);

        List<String> songDataList = new ArrayList<>();

        while (matcher.find()) {
            String songInfo = matcher.group(1).trim() + " - " + matcher.group(2).trim();
            songInfo = songInfo.replaceAll("[0-9]+\\.", "").trim();
            songDataList.add(songInfo);
        }

        //System.out.println(songDataList);
        // 추출된 노래 데이터 출력
        for (String songData : songDataList) {
            System.out.println(songData);
            System.out.println("**");
        }
        return songDataList;
        // ["그냥 보고서 잘 쓰라고 해 - 루비 - 송하영 \n 눈앞에 다가온 거 - 너에게 - 방탄소년"] 이런식으로 나옴(리스트로 쪼개야함)
        /*
[CompletionChoice(text=

1. 눈부셔- 유재하,
2. 내 인생의 노래- 백예린,
3. 나는 바보야- 하현우, index=0, logprobs=null, finish_reason=stop)]
         */
        /*
        줄단위로 쪼갠다.
        시작이 숫자인 문자열만 찾는다.(1, 2, 3)만 따로
        , 단위로 자른다. . 단위로도 자른다 그러면 노래-가수 만 남는다.
         */
    }


}

