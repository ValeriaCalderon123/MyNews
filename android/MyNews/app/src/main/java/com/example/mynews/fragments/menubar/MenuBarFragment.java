package com.example.mynews.fragments.menubar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.mynews.R;

import java.util.ArrayList;


public class MenuBarFragment extends Fragment {
    private View view;
    private ArrayList<ImageButton> imageButtons;
    private LinearLayout linearLayout;

    public MenuBarFragment(){
        this.imageButtons = new ArrayList<ImageButton>();
    }

    public MenuBarFragment(ArrayList<ImageButton> imageButtons) {
        this.imageButtons = imageButtons;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.view == view){
            this.view = inflater.inflate(R.layout.fragment_vertical_menu, container, false);
        }
        this.linearLayout = (LinearLayout) this.view.findViewById(R.id.button_linear_layout);
        for (ImageButton imageButton: this.imageButtons){
            this.linearLayout.addView(imageButton);
        }
        return this.view;
    }

    public void addButtons(ArrayList<ImageButton> imageButtons){
        for (ImageButton imageButton: imageButtons){
            this.linearLayout.addView(imageButton);
        }
        this.imageButtons.addAll(imageButtons);
    }

    public void addButtons(ArrayList<ImageButton> imageButtons, int index){
        for (int i = 0; i < imageButtons.size(); i++){
            System.out.println(this.linearLayout.getChildCount() + " / " + (i+index) + "-------------------------");
            this.linearLayout.addView(imageButtons.get(i), index+i);
        }
        this.imageButtons.addAll(index, imageButtons);
    }

    public void resetBackgroundForAllImageButtons(){
        for (ImageButton imageButton:imageButtons){
            imageButton.setBackground(null);
        }
    }

    public ImageButton getImageButton(int i){
        return this.imageButtons.get(i);
    }
}