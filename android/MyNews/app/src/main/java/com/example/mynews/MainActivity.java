package com.example.mynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.mynews.fragments.NewsFragment;
import com.example.mynews.login.LogUser;
import com.example.mynews.models.Article;
import com.example.mynews.services.mynews.MyNewsRetrofit;
import com.example.mynews.toast.ShowToast;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NewsFragment newsFragment;
    private MyNewsRetrofit retrofit;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUser.currentLogUser = new LogUser(this);
        LogUser.currentLogUser.validatelog();
        this.newsFragment = new NewsFragment(new ArrayList<Article>());
        this.retrofit = new MyNewsRetrofit();
        this.searchView = (SearchView) this.findViewById(R.id.search_input_view);
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ShowToast.show(getApplicationContext(), "Buscando " + searchView.getQuery() +", espere por favor.");
                String search_key = searchView.getQuery().toString();
                retrofit.search(search_key).enqueue(new Callback<ArrayList<Article>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Article>> call, Response<ArrayList<Article>> response) {
                        if (response.isSuccessful()){
                            System.out.println(response.body());
                            newsFragment = new NewsFragment(response.body());
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, newsFragment);
                            transaction.commit();
                        } else {
                            try {
                                ShowToast.show(getApplicationContext(), response.errorBody().string().toString());
                            } catch (IOException e) {
                                ShowToast.show(getApplicationContext(), e.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Article>> call, Throwable t) {
                        ShowToast.show(getApplicationContext(), t.getMessage());
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }
}