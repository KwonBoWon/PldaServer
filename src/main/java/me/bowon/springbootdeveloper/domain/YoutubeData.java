package me.bowon.springbootdeveloper.domain;

import lombok.Getter;

@Getter
public class YoutubeData {
    private String videoId;
    private String tumbnailUrl;
    private String title;
    public YoutubeData(String videoId, String thumbnailUrl, String title){
        this.videoId = videoId;
        this.tumbnailUrl = thumbnailUrl;
        this.title = title;
    }
}