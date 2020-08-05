package com.example.mynews.services.mynews;

import com.example.mynews.models.Article;
import com.example.mynews.models.Calification;
import com.example.mynews.models.Category;
import com.example.mynews.models.Session;
import com.example.mynews.models.Source;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MyNewsService {

    @POST("auth/")
    @FormUrlEncoded
    public Call<Session> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("article/search/{key}")
    public Call<ArrayList<Article>> search(
            @Header("Authorization") String token,
            @Path("key") String key
    );


    @PUT("source/calificate/{uuid}")
    public Call<Source> calificate(
            @Header("Authorization") String token,
            @Path("uuid") String uuid,
            @Body Calification calification
            );

    @GET("category/")
    public Call<ArrayList<Category>> listCategories(
            @Header("Authorization") String token
    );

    @GET("article/category/{pk}")
    public Call<ArrayList<Article>> articlesByCategory(
            @Header("Authorization") String token,
            @Path("pk") int pk
    );

    @GET("logout/")
    public Call<Object> logout(
            @Header("Authorization") String token
    );
}
