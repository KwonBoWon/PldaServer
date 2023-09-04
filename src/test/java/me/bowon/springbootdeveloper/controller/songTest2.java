package me.bowon.springbootdeveloper.controller;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class songTest2 {

    public static void main(String[] args) {
        String text = "[CompletionChoice(text=\n" +
                "\n" +
                "1. 여기엔 내 마음대로 - 방탄소년단\n" +
                "2. 부담감 - 아이유\n" +
                "3. 나를 믿어줘 - 다비치, index=0, logprobs=null, finish_reason=stop)]";

        // 노래 제목과 가수를 추출하는 정규 표현식 패턴
        Pattern pattern = Pattern.compile("[0-9]+\\.\\s+([^\\n]+) - ([^,]+)");

        Matcher matcher = pattern.matcher(text);

        List<String> songDataList = new ArrayList<>();

        while (matcher.find()) {
            String songInfo = matcher.group(1).trim() + " - " + matcher.group(2).trim();
            songDataList.add(songInfo);
        }

        //System.out.println(songDataList);
        // 추출된 노래 데이터 출력

        for (String songData : songDataList) {
            System.out.println(songData.replaceAll("[0-9]+\\.", "").trim());
            //System.out.println(songData);
        }
    }
}


