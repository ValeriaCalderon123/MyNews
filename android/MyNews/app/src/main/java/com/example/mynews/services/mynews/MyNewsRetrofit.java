package com.example.mynews.services.mynews;

import com.example.mynews.login.LogUser;
import com.example.mynews.models.Article;
import com.example.mynews.models.Calification;
import com.example.mynews.models.Category;
import com.example.mynews.models.Session;
import com.example.mynews.models.Source;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyNewsRetrofit {
    private Retrofit retrofit;
    private String baseURL = "https://mynews2020.herokuapp.com/api/v1/";
    private MyNewsService service;

    public MyNewsRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(MyNewsService.class);
    }

    public Call<Session> login(String username, String password) {
        return this.service.login(username, password);
    }

    public Call<ArrayList<Article>> search(String key_word){
        return this.service.search(
                "token " + LogUser.currentLogUser.getSession().getToken()
                , key_word);
    }

    public Call<Source> calificate(String uuid, int calification){
        return this.service.calificate(
                "token " + LogUser.currentLogUser.getSession().getToken(),
                uuid,
                new Calification(calification)
        );
    }

    public Call<ArrayList<Category>> getCategories(){
        return this.service.listCategories(
                "token " + LogUser.currentLogUser.getSession().getToken()
        );
    }

    public Call<ArrayList<Article>> getArticlesByCategory(int pk){
        return this.service.articlesByCategory(
                "token " + LogUser.currentLogUser.getSession().getToken()
                , pk);
    }

    public Call<Object> logout(){
        return this.service.logout(
                "token " + LogUser.currentLogUser.getSession().getToken()
        );
    }

}
