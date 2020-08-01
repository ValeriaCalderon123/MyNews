package com.example.mynews.services.mynews;

import com.example.mynews.models.Session;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyNewsService {

    @POST("auth/")
    @FormUrlEncoded
    public Call<Session> login(
            @Field("username") String username,
            @Field("password") String password
    );
}
