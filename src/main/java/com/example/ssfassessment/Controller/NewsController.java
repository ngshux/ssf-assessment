package com.example.ssfassessment.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ssfassessment.model.Article;
import com.example.ssfassessment.service.NewsService;

@Controller
@RequestMapping (path ="/")
public class NewsController {

    @Autowired
    NewsService service;

    @GetMapping
    public String getArticles(Model model){
        List<Article> articleList = service.getArticles();
        model.addAttribute("articles", articleList);
        return "index";
    }

   // @PostMapping("save")
    //public String saveArticle(@RequestBody MultiValueMap)
}
