package com.example.mynews;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;

import com.example.mynews.callbacks.ArticlesListCallBack;
import com.example.mynews.fragments.FragmentManager;
import com.example.mynews.login.LogUser;
import com.example.mynews.models.Category;
import com.example.mynews.services.mynews.MyNewsRetrofit;
import com.example.mynews.toast.ShowToast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MyNewsRetrofit retrofit;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.validateUser();
        this.getSupportActionBar().hide();
        this.retrofit = new MyNewsRetrofit();
        this.loadCategoryFragment();
        this.loadSearchView();
    }

    private void validateUser() {
        LogUser.currentLogUser = new LogUser(this);
        LogUser.currentLogUser.validatelog();
    }

    private void loadSearchView() {
        this.searchView = (SearchView) this.findViewById(R.id.search_input_view);
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ShowToast.show(getApplicationContext(), "Buscando " + query + ", espere por favor.");
                retrofit.search(query).enqueue(new ArticlesListCallBack(MainActivity.this));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }


    public void loadCategoryFragment(){
        this.retrofit.getCategories().enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if (response.isSuccessful()) {
                    FragmentManager.categoryFragment.setDataSet(response.body());
                    FragmentManager.changeFragment(MainActivity.this, FragmentManager.categoryFragment);
                } else {
                    FragmentManager.changeFragment(MainActivity.this, FragmentManager.errorFragment);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                FragmentManager.changeFragment(MainActivity.this, FragmentManager.errorFragment);
            }
        });
    }
}