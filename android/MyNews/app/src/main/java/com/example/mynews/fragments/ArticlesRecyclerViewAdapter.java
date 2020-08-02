package com.example.mynews.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mynews.R;

import com.example.mynews.models.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ArticlesRecyclerViewAdapter extends RecyclerView.Adapter<ArticlesRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Article> articles;
    private Context context;




    public ArticlesRecyclerViewAdapter(ArrayList<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Article article = this.articles.get(position);
        holder.textTitleNews.setText(article.getTitle());
        holder.textBodyNews.setText(article.getBody());
        Context context = holder.imageNews.getContext();
        Picasso.get().load(article.getImage()).into(holder.imageNews);
        holder.readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(article.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                ArticlesRecyclerViewAdapter.this.context.startActivity(intent);

            }
        });
    }

    public void setDataset(ArrayList<Article> articles){
        this.articles = articles;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageNews;
        public final TextView textTitleNews;
        public final TextView textBodyNews;
        public final ImageButton readButton;
        public ViewHolder(View view) {
            super(view);
            this.imageNews = (ImageView) view.findViewById(R.id.imageNews);
            this.textTitleNews = (TextView) view.findViewById(R.id.textTitleNews);
            this.textBodyNews = (TextView) view.findViewById(R.id.textBodyNews);
            this.readButton = (ImageButton) view.findViewById(R.id.buttonRead);
        }

     }
}