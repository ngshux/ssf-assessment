package com.example.ssfassessment.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.ssfassessment.model.Article;

@Service
public class NewsService {
    private static final String URL ="https://min-api.cryptocompare.com/data/v2/news/?lang=EN";
    
    @Value("${apikey}")
    private String apiKey ="8915bc388adff9ed1a594a7cecfbede887a6e34ae3178f2b747e16e8b0e827f3";

    public static List<Article> getArticles(){
        String articleURL = UriComponentsBuilder.fromUriString(URL)
                            .path("articles")
                            .toUriString();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

        List<Article> articleList = new LinkedList<>();
        try {
            resp = template.getForEntity(articleURL, String.class);

            articleList = Article.create(resp.getBody());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleList;
    }
}
