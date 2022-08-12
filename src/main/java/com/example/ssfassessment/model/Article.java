package com.example.ssfassessment.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Article {
    private static final Logger logger = LoggerFactory.getLogger(Article.class);

    private String id;
    private String published_on;
    private String title;
    private String url;
    private String imageurl;
    private String body;
    private String tags;
    private String categories;

    public static List<Article> create(String json) throws IOException{
        List<Article> aList = new LinkedList<>();
        
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject allArticles = r.readObject();
            JsonArray data = allArticles.getJsonArray("Data");

            for(String article : allArticles.keySet()){
                Article a = new Article();
                for (int i = 0; i < data.size(); i++) {
                    JsonObject art = data.getJsonObject(i);
                    a.id = art.getString("id");
                    a.title = art.getString("title");
                    a.published_on = art.getString("published_on");
                    a.url = art.getString("url");
                    a.imageurl = art.getString("imageurl");
                    a.body = art.getString("body");
                    a.categories = art.getString("categories");
                    a.tags = art.getString("tags");
                    logger.info("TAGS " + a.tags + "CATS " + a.categories);
                    aList.add(a);
                }
            }
        }
        return aList;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublished_on() {
        return published_on;
    }

    public void setPublished_on(String published_on) {
        this.published_on = published_on;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    
}
