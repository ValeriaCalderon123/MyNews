package com.example.mynews.onClickListeners;

import android.view.View;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.mynews.R;
import com.example.mynews.fragments.FragmentManager;

public class MenuBarOnClickListener implements View.OnClickListener {

    private ImageButton button;
    private Fragment fragment;
    private FragmentActivity fragmentActivity;

    public MenuBarOnClickListener(ImageButton imageButton, Fragment fragment, FragmentActivity fragmentActivity){
        this.button = imageButton;
        this.fragment = fragment;
        this.fragmentActivity = fragmentActivity;
    }


    @Override
    public void onClick(View v) {
        FragmentManager.changeFragment(this.fragmentActivity, this.fragment);
        this.button.setBackgroundColor(this.fragmentActivity.getResources().getColor(R.color.button_bar_pressed));
    }
}
