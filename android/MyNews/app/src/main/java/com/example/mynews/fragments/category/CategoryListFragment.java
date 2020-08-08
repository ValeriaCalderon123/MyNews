package com.example.mynews.fragments.category;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynews.R;
import com.example.mynews.callbacks.CategoryListCallBack;
import com.example.mynews.fragments.categories.CategoryRecyclerViewAdapter;
import com.example.mynews.services.mynews.MyNewsRetrofit;

public class CategoryListFragment extends Fragment {
    private View view;
    private CategoryListRecyclerViewAdapter adapter;
    private MyNewsRetrofit retrofit;
    private RecyclerView recyclerView;

    public CategoryListFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.view == null){
            this.view = inflater.inflate(R.layout.fragment_item_list_category, container, false);
            this.adapter = new CategoryListRecyclerViewAdapter();
            this.recyclerView = (RecyclerView) view.findViewById(R.id.list_category__);
            this.recyclerView.setHasFixedSize(true);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            this.recyclerView.setAdapter(this.adapter);
            this.retrofit = new MyNewsRetrofit();
        }
        return this.view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.retrofit.getCategories().enqueue(new CategoryListCallBack(this.getContext(), this.adapter, this.getActivity()));
    }
}