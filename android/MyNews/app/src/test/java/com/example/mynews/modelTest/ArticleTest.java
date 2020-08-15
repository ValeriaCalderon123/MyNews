package com.example.mynews.modelTest;

import com.example.mynews.models.Article;
import com.example.mynews.models.Session;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleTest {

    Article session = new Article("Cuarentena en Perú","Aumentan la cuarentena en algunos departamentos en peru","coronavirus.jpg");

    @org.junit.jupiter.api.Test
    void getTitle() {
        assertEquals("Cuarentena en Perú",session.getTitle());
    }

    @org.junit.jupiter.api.Test
    void getBody() {
        assertEquals("Aumentan la cuarentena en algunos departamentos en peru",session.getBody());
    }

    @org.junit.jupiter.api.Test
    void getImagen() {
        assertEquals("coronavirus.jpg",session.getImage());
    }

    @org.junit.jupiter.api.Test
    void setTitle(){
        session.setTitle("Cuarentena en Bolivia");
        assertEquals("Cuarentena en Bolivia",session.getTitle());
    }
    @org.junit.jupiter.api.Test
    void setBody(){
        session.setBody("No habra mas cuarentena en Bolivia");
        assertEquals("No habra mas cuarentena en Bolivia",session.getBody());
    }
    @org.junit.jupiter.api.Test
    void setImage(){
        session.setImage("corona.jpg");
        assertEquals("corona.jpg",session.getImage());
    }
}
