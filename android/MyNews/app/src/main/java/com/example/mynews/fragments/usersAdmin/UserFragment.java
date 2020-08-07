package com.example.mynews.fragments.usersAdmin;

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
import com.example.mynews.callbacks.AdminListCallBack;
import com.example.mynews.models.User;
import com.example.mynews.services.mynews.MyNewsRetrofit;

import java.util.ArrayList;

public class UserFragment extends Fragment {
    private View view;
    private  RecyclerView recyclerView;
    private UserRecyclerViewAdapter userRecyclerViewAdapter;
    private ArrayList<User> users;
    private boolean hasToUpdate;
    private MyNewsRetrofit retrofit;

    public UserFragment() {
        this.hasToUpdate = false;
        this.retrofit = new MyNewsRetrofit();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setHasToUpdate(boolean hasToUpdate){
        this.hasToUpdate = hasToUpdate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.view == null){
            this.view = inflater.inflate(R.layout.fragment_user_list, container, false);
            this.userRecyclerViewAdapter = new UserRecyclerViewAdapter();
            this.recyclerView = (RecyclerView) this.view.findViewById(R.id.list_admin);
            this.recyclerView.setHasFixedSize(true);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            this.recyclerView.setAdapter(this.userRecyclerViewAdapter);
            this.updateData();

        }

        return this.view;
    }

    public void updateData(){
        this.retrofit.getAdminUserList().enqueue(new AdminListCallBack(this.getContext(), this.userRecyclerViewAdapter, this.getActivity()));
    }

}