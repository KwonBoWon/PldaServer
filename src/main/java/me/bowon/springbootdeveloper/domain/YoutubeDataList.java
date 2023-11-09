package me.bowon.springbootdeveloper.domain;

import lombok.Getter;

import java.util.List;
@Getter
public class YoutubeDataList {
    private List<YoutubeData> youtubeDataList;
    public YoutubeDataList(List<YoutubeData> youtubeData){
        this.youtubeDataList = youtubeData;
    }
}
