package me.bowon.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.bowon.springbootdeveloper.domain.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class GptService {

    public List<Song> parseSong(String data) {
        System.out.println(data);
        // 1-3.으로 시작하는 패턴
        Pattern pattern = Pattern.compile("^[1-3].*");
        String [] arr = data.split("\n");
        List<String> list = new ArrayList<>();
        List<Song> songs = new ArrayList<>();
        for (String string: arr){
            string = string.replaceAll("Answer: ", "");
            string = string.replaceAll("Desired Output: ", "");
            System.out.println("^^" + string);
            Matcher matcher = pattern.matcher(string);
            if(matcher.matches()){
                System.out.println("OKAY\n");
                String [] tmp = string.split(",");
                tmp[0] = tmp[0].replaceAll("^[1-3].", ""); // 앞의 1-3. 삭제
                tmp[0] = tmp[0].replaceAll(" ", ""); // 공백 삭제
                list.add(tmp[0]);
                System.out.println("&&" + tmp[0]);
            }
        }
        for (String string: list){
            String [] tmp = string.split("-");
            Song song = new Song(tmp[0], tmp[1]);
            System.out.println("@" + tmp[0]+ "#" +tmp[1]);
            songs.add(song);
        }
        return songs;
    }


}

