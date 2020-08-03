package com.example.mynews.fragments.categories;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynews.R;
import com.example.mynews.models.Category;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class CategoryFragment extends Fragment {

    private int mColumnCount = 2;
    private RecyclerView recyclerView;
    private CategoryRecyclerViewAdapter categoryRecyclerViewAdapter;
    private ArrayList<Category> categories;
    private View view;

    public CategoryFragment(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public void setDataSet(ArrayList<Category> categories){
        this.categories = categories;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.view== null)
            this.view = inflater.inflate(R.layout.fragment_category_list, container, false);
        this.categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(this.categories, this);
        this.recyclerView = (RecyclerView) view.findViewById(R.id.list_categories);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), mColumnCount));
//        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        this.recyclerView.setAdapter(this.categoryRecyclerViewAdapter);
        this.categoryRecyclerViewAdapter.setDataset(this.categories);
        return view;
    }
}