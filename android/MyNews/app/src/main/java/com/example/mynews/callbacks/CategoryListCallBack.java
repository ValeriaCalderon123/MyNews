package com.example.mynews.callbacks;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.example.mynews.fragments.FragmentManager;
import com.example.mynews.fragments.categories.CategoryRecyclerViewAdapter;
import com.example.mynews.fragments.category.CategoryListRecyclerViewAdapter;
import com.example.mynews.models.Category;
import com.example.mynews.toast.ShowToast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryListCallBack implements Callback<ArrayList<Category>> {
    private Context context;
    private CategoryListRecyclerViewAdapter adapter;
    private FragmentActivity activity;

    public CategoryListCallBack(Context context, CategoryListRecyclerViewAdapter adapter, FragmentActivity activity) {
        this.context = context;
        this.adapter = adapter;
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
        if (response.isSuccessful()){
            ArrayList<Category> categories = response.body();
            if (categories.isEmpty()){
                FragmentManager.changeFragment(this.activity, FragmentManager.noResultsFragment);
            } else {
                adapter.setDataSet(categories);
            }
        } else {
            ShowToast.show(this.context, "Error interno, vuelve a intentar más tarde.");
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
        if (this.adapter.getItemCount()==0){
            FragmentManager.changeFragment(this.activity, FragmentManager.errorFragment);
        } else {
            ShowToast.show(this.context, "Error de conexión.");
        }
    }
}
