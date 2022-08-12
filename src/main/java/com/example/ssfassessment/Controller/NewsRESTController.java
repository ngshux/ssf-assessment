package com.example.ssfassessment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssfassessment.model.Article;
import com.example.ssfassessment.service.NewsService;

@RestController
@RequestMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsRESTController {
    
    @Autowired
    NewsService service;

    @GetMapping(path ="/articles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article[]> getAllArticles(){
        Article[] aArr = service.getSavedArticles();
        return ResponseEntity.ok(aArr);
    }
}
