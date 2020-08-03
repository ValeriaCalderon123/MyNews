package com.example.mynews.onClickListeners;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mynews.R;
import com.example.mynews.fragments.article.NewsFragment;
import com.example.mynews.models.Article;
import com.example.mynews.services.mynews.MyNewsRetrofit;
import com.example.mynews.toast.ShowToast;

import java.io.IOException;
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
        retrofit.getArticlesByCategory(this.pk).enqueue(new Callback<ArrayList<Article>>() {
            @Override
            public void onResponse(Call<ArrayList<Article>> call, Response<ArrayList<Article>> response) {
                if (response.isSuccessful()){
                    NewsFragment newsFragment = new NewsFragment(response.body());
                    FragmentTransaction transaction = fragment.getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, newsFragment);
                    transaction.commit();
                } else {
                    ShowToast.show(fragment.getContext(), "No se puedo acceder a esta categoria");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Article>> call, Throwable t) {
                ShowToast.show(fragment.getContext(), t.getMessage());
            }
        });
    }
}
