package com.example.mynews.fragments.article;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynews.R;
import com.example.mynews.models.Article;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class NewsFragment extends Fragment {

    private  RecyclerView recyclerView;
    private ArticlesRecyclerViewAdapter articlesRecyclerViewAdapter;
    private ArrayList<Article> articles;
    public NewsFragment(ArrayList<Article> articles) {
        this.articles = articles;

    }

    public void setDataset(ArrayList<Article> articles){
        this.articlesRecyclerViewAdapter.setDataset(articles);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.articlesRecyclerViewAdapter = new ArticlesRecyclerViewAdapter(this.articles, getContext());
        this.recyclerView = (RecyclerView) this.getActivity().findViewById(R.id.list_news);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        this.recyclerView.setAdapter(this.articlesRecyclerViewAdapter);
        this.articlesRecyclerViewAdapter.setDataset(this.articles);


    }
}