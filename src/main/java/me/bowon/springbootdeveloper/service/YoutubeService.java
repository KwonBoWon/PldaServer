package me.bowon.springbootdeveloper.service;


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import lombok.RequiredArgsConstructor;
import me.bowon.springbootdeveloper.domain.Song;
import me.bowon.springbootdeveloper.domain.YoutubeData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class YoutubeService {

    @Value("${youtube.api-key}")
    private String API_KEY;
    @Value("${youtube.application-name}")
    private String APPLICATION_NAME;
    public final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();


    public List<YoutubeData> youtubeApi(List<Song> songs) throws GeneralSecurityException, IOException {
        List<YoutubeData> youtubeDataList = new ArrayList<>();
        for (Song song :songs) {
            String request = song.getSongName() + "-" + song.getSinger();
            SearchListResponse searchListResponse = youtubeSearch(request);
            youtubeDataList.add(parseSongSinger(searchListResponse));
        }

        return youtubeDataList;
    }
    public YoutubeData parseSongSinger(SearchListResponse response){
        List<SearchResult> searchResultList = response.getItems();
        SearchResult result = searchResultList.get(0);
        ResourceId resourceId = result.getId();
        SearchResultSnippet snippet = result.getSnippet();

        YoutubeData data = new YoutubeData(
                resourceId.getVideoId(), snippet.getThumbnails().getHigh().getUrl(),snippet.getTitle());
        return data;
    }
    public SearchListResponse youtubeSearch(String request)
            throws GeneralSecurityException, IOException, GoogleJsonResponseException
    {
        YoutubeService youtubeService = new YoutubeService();
        YouTube youtube = youtubeService.getService();

        YouTube.Search.List searchRequest = youtube.search()
                .list("snippet");
        SearchListResponse response = searchRequest.setKey(API_KEY)
                .setMaxResults(1L)
                .setQ(request)
                .execute();
        System.out.println(response);
        return response;
    }
    public YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

}
