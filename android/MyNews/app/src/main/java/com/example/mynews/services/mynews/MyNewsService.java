package com.example.mynews.services.mynews;

import com.example.mynews.models.Article;
import com.example.mynews.models.Calification;
import com.example.mynews.models.Category;
import com.example.mynews.models.Session;
import com.example.mynews.models.Source;
import com.example.mynews.models.User;

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

    @POST("user/")
    @FormUrlEncoded
    public Call<User> registerUser(
            @Field("username") String username,
            @Field("last_name") String last_name,
            @Field("first_name") String first_name,
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("auth/user/")
    public Call<User> userAuthenticated(
            @Header("Authorization") String token
    );

    @GET("user/admin/")
    public Call<ArrayList<User>> getAdminUserList(
            @Header("Authorization") String token
    );

    @PUT("user/admin/{username}")
    public Call<User> userToSuperUser(
            @Header("Authorization") String token,
            @Path("username") String username
    );

    @GET("source/")
    public Call<ArrayList<Source>> getSourceList(
            @Header("Authorization") String token
    );
}
