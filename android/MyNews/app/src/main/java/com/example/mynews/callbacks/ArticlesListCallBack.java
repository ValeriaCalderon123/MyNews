package com.example.mynews.callbacks;

import androidx.fragment.app.FragmentActivity;
import com.example.mynews.fragments.FragmentManager;
import com.example.mynews.fragments.article.NewsFragment;
import com.example.mynews.models.Article;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesListCallBack implements Callback<ArrayList<Article>> {

    public FragmentActivity activity;

    public ArticlesListCallBack(FragmentActivity activity){
        this.activity = activity;
    }

    public void onResponse(Call<ArrayList<Article>> call, Response<ArrayList<Article>> response) {
        if (response.isSuccessful()) {
            ArrayList<Article> articles = response.body();
            if (!articles.isEmpty()){
                FragmentManager.newsFragment = new NewsFragment(articles);
                FragmentManager.changeFragment(this.activity, FragmentManager.newsFragment);
            } else {
                FragmentManager.changeFragment(this.activity, FragmentManager.noResultsFragment);
            }

        } else {
            FragmentManager.changeFragment(this.activity, FragmentManager.errorFragment);
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Article>> call, Throwable t) {
        FragmentManager.changeFragment(this.activity, FragmentManager.errorFragment);
    }
}

