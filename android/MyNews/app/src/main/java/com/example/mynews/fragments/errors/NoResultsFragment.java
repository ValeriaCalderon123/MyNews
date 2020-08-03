package com.example.mynews.fragments.errors;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynews.R;


public class NoResultsFragment extends Fragment {

    private View view;

    public NoResultsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.view==null)
            this.view = inflater.inflate(R.layout.fragment_no_results, container, false);
        return this.view;
    }
}