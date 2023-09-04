package me.bowon.springbootdeveloper.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongDataExtractor {

    public static void main(String[] args) {
        String text = "[CompletionChoice(text=\n" +
                "\n" +
                "1. 여기엔 내 마음대로 - 방탄소년단\n" +
                "2. 부담감 - 아이유\n" +
                "3. 나를 믿어줘 - 다비치, index=0, logprobs=null, finish_reason=stop)]";



        // 텍스트를 줄 바꿈 문자("\n")로 분리
        String[] lines = text.split("\\n");

        // "노래이름-가수" 형식의 데이터를 저장할 리스트 생성
        List<String> songDataList = new ArrayList<>();

        for (String line : lines) {
            // 숫자와 "노래이름-가수"를 분리
            String[] parts = line.split("\\.\\s+");
            if (parts.length == 2) {
                String songInfo = parts[1].trim();
                // "노래이름-가수" 데이터를 저장
                songDataList.add(songInfo);
            }
        }

        // 추출된 데이터 출력
        for (String songData : songDataList) {
            System.out.println(songData);
        }
    }
}