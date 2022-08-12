package com.example.ssfassessment.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.ssfassessment.model.Article;

@Service
public class NewsService {
    private static final String URL ="https://min-api.cryptocompare.com/data/v2/news/?lang=EN";
    
    //@Value("${apikey}")
    private String apiKey = System.getenv("apiKey");

    public List<Article> getArticles(){
        String articleURL = UriComponentsBuilder.fromUriString(URL)
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

    @Autowired
    RedisTemplate<String,Article> redisTemplate;
    public void saveArticles(Article arti){
        redisTemplate.opsForValue().set(arti.getId(), arti);
        redisTemplate.opsForValue().set(arti.getTitle(), arti);
        redisTemplate.opsForValue().set(arti.getUrl(), arti);
        redisTemplate.opsForValue().set(arti.getImageurl(), arti);
        redisTemplate.opsForValue().set(arti.getBody(), arti);
        redisTemplate.opsForValue().set(arti.getTags(), arti);
        redisTemplate.opsForValue().set(arti.getCategories(), arti);
        redisTemplate.opsForValue().set(arti.getPublished_on(), arti);
    }

    public Article[] getSavedArticles(){
        Set<String> allArticleKeys = redisTemplate.keys("*");
        List<Article> aArr = new LinkedList<Article>();
        for ( String artiKey : allArticleKeys){
            Article result = (Article) redisTemplate.opsForValue().get(artiKey);
            aArr.add(result);
        }
        return aArr.toArray(new Article[aArr.size()]);
    }
}
