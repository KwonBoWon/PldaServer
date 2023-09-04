package me.bowon.springbootdeveloper.controller;

/**
 * Sample Java code for youtube.search.list
 * See instructions for running these code samples locally:
 * https://developers.google.com/explorer-help/code-samples#java
 */

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
@RestController
@RequestMapping(value = "/youtube")
public class YoutubeTest {
    @Value("${youtube.api-key}")
    private String DEVELOPER_KEY;
    @Value("${youtube.application-name}")
    private String APPLICATION_NAME = "My Maps Project";
    public final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();


    @PostMapping("/post")
    public void youtubeSearch(@RequestBody String request)
            throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        YoutubeTest youtubeTest = new YoutubeTest();
        YouTube youtubeService = youtubeTest.getService();

        // Define and execute the API request
        YouTube.Search.List searchRequest = youtubeService.search()
                .list("snippet");
        SearchListResponse response = searchRequest.setKey(youtubeTest.DEVELOPER_KEY)
                .setMaxResults(2L)
                .setQ(request)
                .execute();
        System.out.println(response);
    }



    /**
     * Build and return an authorized API client service.
     *
     * @return an authorized API client service
     * @throws GeneralSecurityException, IOException
     */
    public YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new YouTube.Builder(httpTransport, JSON_FACTORY, null)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     *
     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
     */
    public static void main(String[] args)
            throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        YoutubeTest youtubeTest = new YoutubeTest();
        YouTube youtubeService = youtubeTest.getService();

        // Define and execute the API request
        YouTube.Search.List request = youtubeService.search()
                .list("snippet");
        SearchListResponse response = request.setKey(youtubeTest.DEVELOPER_KEY)
                .setMaxResults(2L)
                .setQ("request")
                .execute();
        System.out.println(response);
    }
}