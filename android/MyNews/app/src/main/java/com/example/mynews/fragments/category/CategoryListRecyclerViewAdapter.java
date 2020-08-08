package com.example.mynews.fragments.category;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynews.R;
import com.example.mynews.models.Category;

import java.util.ArrayList;


public class CategoryListRecyclerViewAdapter extends RecyclerView.Adapter<CategoryListRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Category> categories;

    public CategoryListRecyclerViewAdapter() {
        this.categories = new ArrayList<Category>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_category, parent, false);
        return new ViewHolder(view);
    }

    public void setDataSet(ArrayList<Category> categories){
        this.categories = categories;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Category category = this.categories.get(position);
        holder.categoryNameTextView.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return this.categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView categoryNameTextView;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.categoryNameTextView = (TextView) this.view.findViewById(R.id.category_name);
        }
    }
}