package com.example.mynews.modelTest;

import com.example.mynews.models.Article;
import com.example.mynews.models.Category;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {
    Category category = new Category(5,"POLITICA");

    @org.junit.jupiter.api.Test
    void getId() {
        assertEquals(5,category.getId());
    }
    @org.junit.jupiter.api.Test
    void getName() {
        assertEquals("POLITICA",category.getName());
    }

    @org.junit.jupiter.api.Test
    void setBody() {
        category.setName("MUNDO");
        assertEquals("MUNDO",category.getName());
    }
}
