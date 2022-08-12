package com.example.ssfassessment.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ssfassessment.model.Article;
import com.example.ssfassessment.service.NewsService;

@Controller
@RequestMapping (path ="/index")
public class NewsController {
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    NewsService service;

    @GetMapping (produces = ("text/html"))
    public String getArticles(Model model){
        List<Article> articleList = service.getArticles();
        model.addAttribute("articles", articleList);
        return "index";
    }

    @PostMapping("/articles")
    public String saveArticle(Model model){
        return "articles";
    }
}
