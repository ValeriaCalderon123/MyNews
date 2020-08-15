package com.example.mynews.fragments.categories;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.mynews.R;
import com.example.mynews.models.Category;
import com.example.mynews.onClickListeners.CategoryListener;
import java.util.ArrayList;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Category> categories;
    private Fragment fragment;

    public CategoryRecyclerViewAdapter(ArrayList<Category> items, Fragment fragment) {
        this.categories = items;
        this.fragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Category category = this.categories.get(position);
        holder.buttonCategory.setText(category.getName());
        holder.buttonCategory.setOnClickListener(new CategoryListener(category.getId(), this.fragment));
    }

    @Override
    public int getItemCount() {
        return this.categories.size();
    }

    public void setDataset(ArrayList<Category> categories) {
        this.categories = categories;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final Button buttonCategory;

        public ViewHolder(View view) {
            super(view);
            this.buttonCategory = (Button) view.findViewById(R.id.buttonCategory);

        }


    }
}