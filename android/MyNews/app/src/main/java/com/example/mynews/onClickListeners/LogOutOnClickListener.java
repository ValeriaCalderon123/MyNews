package com.example.mynews.onClickListeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.example.mynews.MainActivity;
import com.example.mynews.R;
import com.example.mynews.activities.LoginActivity;
import com.example.mynews.callbacks.EmptyCallBack;
import com.example.mynews.login.LogUser;
import com.example.mynews.services.mynews.MyNewsRetrofit;

public class LogOutOnClickListener implements View.OnClickListener {
    MyNewsRetrofit myNewsRetrofit;
    Activity activity;
    ImageButton imageButton;

    public LogOutOnClickListener(MyNewsRetrofit myNewsRetrofit, Activity activity, ImageButton button){
        this.myNewsRetrofit = myNewsRetrofit;
        this.activity = activity;
        this.imageButton = button;
    }

    @Override
    public void onClick(View v) {
        this.myNewsRetrofit.logout().enqueue(new EmptyCallBack());
        this.imageButton.setBackgroundColor(this.activity.getResources().getColor(R.color.button_bar_pressed));
        LogUser.currentLogUser.logout();
        Intent intent = new Intent(this.activity, LoginActivity.class);
        this.activity.startActivity(intent);
        this.activity.finish();
    }
}
