package com.example.mynews.onClickListeners;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.mynews.callbacks.ArticlesListCallBack;
import com.example.mynews.fragments.FragmentManager;
import com.example.mynews.fragments.article.NewsFragment;
import com.example.mynews.models.Article;
import com.example.mynews.services.mynews.MyNewsRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryListener implements  View.OnClickListener {
    private int pk;
    private Fragment fragment;

    public  CategoryListener (int pk, Fragment fragment){
        this.pk = pk;
        this.fragment = fragment;
    }
    @Override
    public void onClick(View v) {
        MyNewsRetrofit retrofit = new MyNewsRetrofit();
        retrofit.getArticlesByCategory(this.pk).enqueue(new ArticlesListCallBack(this.fragment.getActivity()));
    }
}
