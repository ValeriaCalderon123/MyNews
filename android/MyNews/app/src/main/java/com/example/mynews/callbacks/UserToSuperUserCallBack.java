package com.example.mynews.callbacks;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mynews.fragments.usersAdmin.UserRecyclerViewAdapter;
import com.example.mynews.models.User;
import com.example.mynews.toast.ShowToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserToSuperUserCallBack implements Callback<User> {
    private Context context;
    private UserRecyclerViewAdapter adapter;
    private EditText editText;

    public UserToSuperUserCallBack(Context context, UserRecyclerViewAdapter adapter, EditText editText) {
        this.context = context;
        this.adapter = adapter;
        this.editText = editText;
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response.code() == 404){
            ShowToast.show(this.context, "No se encontro usuario.");
        } else if (response.code() == 412){
            ShowToast.show(this.context, "Usuario ya es un administrador.");
        } else if (response.isSuccessful()){
            this.adapter.addAdmin(response.body(), 0);
            this.editText.setText("");
        } else {
            ShowToast.show(this.context, "Ocurrio un error. Intentelo más tarde");
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
         ShowToast.show(this.context, "No se pudo establecer conección");
    }
}
