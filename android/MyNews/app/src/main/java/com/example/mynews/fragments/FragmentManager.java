package com.example.mynews.fragments;


import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.mynews.R;
import com.example.mynews.fragments.article.NewsFragment;
import com.example.mynews.fragments.categories.CategoryFragment;
import com.example.mynews.fragments.errors.ErrorFragment;
import com.example.mynews.fragments.errors.NoResultsFragment;
import com.example.mynews.fragments.menubar.MenuBarFragment;
import com.example.mynews.models.Article;
import com.example.mynews.models.Category;

import java.util.ArrayList;

public class FragmentManager {

    public static NewsFragment newsFragment = new NewsFragment(new ArrayList<Article>());
    public static CategoryFragment categoryFragment = new CategoryFragment(new ArrayList<Category>());
    public static ErrorFragment errorFragment = new ErrorFragment();
    public static NoResultsFragment noResultsFragment = new NoResultsFragment();
    public static MenuBarFragment menuBarFragment = new MenuBarFragment(new ArrayList<ImageButton>());

    public static void changeFragment(FragmentActivity activity, Fragment fragment){
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commitAllowingStateLoss();
        menuBarFragment.resetBackgroundForAllImageButtons();
    }
}
