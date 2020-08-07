package com.example.mynews.callbacks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageButton;

import com.example.mynews.MainActivity;
import com.example.mynews.activities.LoginActivity;
import com.example.mynews.fragments.FragmentManager;
import com.example.mynews.login.LogUser;
import com.example.mynews.models.User;
import com.example.mynews.toast.ShowToast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAthenticatedCallBack  implements Callback<User> {
    private Context context;
    private Activity activity;
    private ArrayList<ImageButton> buttons;
    private int index;

    public UserAthenticatedCallBack(Context context, Activity activity, ArrayList<ImageButton> buttons, int index) {
        this.context = context;
        this.activity = activity;
        this.buttons = buttons;
        this.index = index;
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response.code() == 401){
            if (LogUser.currentLogUser.isLogged()){
                ShowToast.show(this.context, "La sesión actual caduco.");
                LogUser.currentLogUser.logout();
                Intent intent = new Intent(this.activity, LoginActivity.class);
                this.activity.startActivity(intent);
                this.activity.finish();
            }
        } else if (response.isSuccessful()){
            if (response.body().isIs_superuser()){
                FragmentManager.menuBarFragment.addButtons(this.buttons, index);
            }
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        ShowToast.show(this.context, "No se pudo obtener datos de Usuario. Por favor revisa tu conexión a Internet");
    }
}
