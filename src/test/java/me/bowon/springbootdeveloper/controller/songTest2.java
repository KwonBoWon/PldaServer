package me.bowon.springbootdeveloper.controller;

import me.bowon.springbootdeveloper.domain.Song;
import org.thymeleaf.spring6.processor.SpringSrcTagProcessor;

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
        songTest2 st = new songTest2();
        List<Song> tmp = st.parseSong(text);
        /*String [] arr = text.split("\n");
        List<String> strings = new ArrayList<>();
        List<String> songs = new ArrayList<>();
        Pattern numberPattern = Pattern.compile("^[1-3].*");
        for (String data: arr) {
            Matcher matcher1 = numberPattern.matcher(data);

            if(matcher1.matches()){
                strings.add(data);
            }
        }
        for (String str: strings) {
            String []tmp = str.split(",");
            //System.out.println("%" + tmp[0]);
            Matcher matcher2 = numberPattern.matcher(tmp[0]);
            tmp[0] = tmp[0].replaceAll("^[1-3].", "");
            tmp[0] = tmp[0].replaceAll(" ", "");
            System.out.println("#" + tmp[0]);
            songs.add(tmp[0]);
        }
        for (String st: songs){
            String [] tmp = st.split("-");
            System.out.println("songs:" + tmp[0]);
            System.out.println("singer:" + tmp[1]);
        }*/
    }
    public List<Song> parseSong(String data) {

        // 1-3.으로 시작하는 패턴
        Pattern pattern = Pattern.compile("^[1-3].*");
        String [] arr = data.split("\n");
        List<String> list = new ArrayList<>();
        List<Song> songs = new ArrayList<>();
        for (String string: arr){
            Matcher matcher = pattern.matcher(string);
            if(matcher.matches()){
                //list.add(string);
                String [] tmp = string.split(",");
                tmp[0] = tmp[0].replaceAll("^[1-3].", ""); // 앞의 1-3. 삭제
                tmp[0] = tmp[0].replaceAll(" ", ""); // 공백 삭제
                list.add(tmp[0]);
            }
        }
        for (String string: list){
            System.out.println("%"+ string);
            String [] tmp = string.split("-");
            Song song = new Song(tmp[0], tmp[1]);
            System.out.println("@" + tmp[0]+ "#" +tmp[1]);
            songs.add(song);
            //System.out.println("*" + song.toString());
        }
        return songs;
    }
}


