package com.example.mynews.fragments.source;

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
import com.example.mynews.callbacks.SourceListCallBack;
import com.example.mynews.fragments.usersAdmin.UserRecyclerViewAdapter;
import com.example.mynews.models.Source;
import com.example.mynews.models.User;
import com.example.mynews.services.mynews.MyNewsRetrofit;

import java.util.ArrayList;

public class SourceFragment extends Fragment {
    private View view;
    private  RecyclerView recyclerView;
    private SourceRecyclerViewAdapter sourceRecyclerViewAdapter;
    private MyNewsRetrofit retrofit;

    public SourceFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.view == null){
            this.view = inflater.inflate(R.layout.fragment_item_list_source, container, false);
            this.sourceRecyclerViewAdapter = new SourceRecyclerViewAdapter(new ArrayList<Source>());
            this.retrofit = new MyNewsRetrofit();
            this.recyclerView = (RecyclerView) this.view.findViewById(R.id.list_sources);
            this.recyclerView.setHasFixedSize(true);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            this.recyclerView.setAdapter(this.sourceRecyclerViewAdapter);
        }
        return this.view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.retrofit.getSourceList().enqueue(new SourceListCallBack(this.sourceRecyclerViewAdapter, this.getContext(), this.getActivity()));
    }
}