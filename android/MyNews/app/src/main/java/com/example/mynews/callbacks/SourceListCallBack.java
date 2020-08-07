package com.example.mynews.callbacks;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.example.mynews.fragments.FragmentManager;
import com.example.mynews.fragments.source.SourceRecyclerViewAdapter;
import com.example.mynews.models.Source;
import com.example.mynews.toast.ShowToast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceListCallBack implements Callback<ArrayList<Source>> {
    SourceRecyclerViewAdapter adapter;
    Context context;
    FragmentActivity activity;

    public SourceListCallBack(SourceRecyclerViewAdapter adapter, Context context, FragmentActivity activity) {
        this.adapter = adapter;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<ArrayList<Source>> call, Response<ArrayList<Source>> response) {
        if (response.isSuccessful()){
            ArrayList<Source> sources = response.body();
            if (sources.size()==0){
                FragmentManager.changeFragment(this.activity, FragmentManager.noResultsFragment);
            } else {
                this.adapter.setDataSet(sources);
            }
        } else {
            FragmentManager.changeFragment(this.activity, FragmentManager.errorFragment);
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Source>> call, Throwable t) {
        if (adapter.getItemCount()==0){
            FragmentManager.changeFragment(this.activity, FragmentManager.errorFragment);
        } else {
            ShowToast.show(this.context, "No se pudo actualizar debido a error de conexión.");
        }

    }
}
