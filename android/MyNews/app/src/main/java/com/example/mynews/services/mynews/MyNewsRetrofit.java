package com.example.mynews.services.mynews;

import com.example.mynews.models.Session;

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
}
