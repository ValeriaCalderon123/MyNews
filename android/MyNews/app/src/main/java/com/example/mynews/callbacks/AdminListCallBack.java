package com.example.mynews.callbacks;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.example.mynews.fragments.FragmentManager;
import com.example.mynews.fragments.usersAdmin.UserRecyclerViewAdapter;
import com.example.mynews.models.User;
import com.example.mynews.toast.ShowToast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminListCallBack implements Callback<ArrayList<User>> {
    private Context context;
    private UserRecyclerViewAdapter userRecyclerViewAdapter;
    private FragmentActivity activity;

    public AdminListCallBack(Context context, UserRecyclerViewAdapter userRecyclerViewAdapter, FragmentActivity activity) {
        this.context = context;
        this.userRecyclerViewAdapter = userRecyclerViewAdapter;
        this.activity = activity;
    }


    @Override
    public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
        if (response.isSuccessful()){
            this.userRecyclerViewAdapter.setDataset(response.body());
        } else if (response.code() == 401){
            ShowToast.show(this.context, "Lo sentimos no tienes acceso a este contenido.");
        } else {
            ShowToast.show(this.context, "Lo sentimos ocurrio un error vuelve a intentar mas tarde.");
        }
    }

    @Override
    public void onFailure(Call<ArrayList<User>> call, Throwable t) {
        FragmentManager.changeFragment(this.activity, FragmentManager.errorFragment);
    }
}
