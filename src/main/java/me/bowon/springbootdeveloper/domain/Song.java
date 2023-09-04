package me.bowon.springbootdeveloper.domain;

import lombok.Getter;

@Getter
public class Song{
    private String songName;
    private String singer;
    public Song(String songName, String singer){
        this.songName = songName;
        this.singer = singer;
    }
}