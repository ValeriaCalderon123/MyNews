package com.example.mynews.fragments.useradminoperations;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mynews.R;
import com.example.mynews.fragments.FragmentManager;
import com.example.mynews.fragments.usersAdmin.UserFragment;
import com.example.mynews.fragments.usersAdmin.UserRecyclerViewAdapter;
import com.example.mynews.onClickListeners.UserToSuperUserOnClickListener;
import com.example.mynews.services.mynews.MyNewsRetrofit;

public class UserAdminOperationsFragment extends Fragment {
    private View view;
    private UserFragment fragment;
    private EditText username_input;
    private MyNewsRetrofit retrofit;
    private UserRecyclerViewAdapter adapter;
    private Button button;



    public UserAdminOperationsFragment() {
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.view == null){
            this.view = inflater.inflate(R.layout.fragment_user_admin_operations, container, false);
            this.username_input = (EditText) this.view.findViewById(R.id.nameuser_new_admin);
            this.retrofit = new MyNewsRetrofit();
            this.button = (Button) this.view.findViewById(R.id.button_new_admin);
        }
        return this.view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.fragment = FragmentManager.userFragment;
        this.adapter = this.fragment.getAdapter();
        this.button.setOnClickListener(new UserToSuperUserOnClickListener(this.username_input, this.retrofit, this.getContext(), this.adapter));

    }
}