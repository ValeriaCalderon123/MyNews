package com.example.mynews.login;

import android.app.Activity;
import android.content.Intent;

import com.example.mynews.activities.LoginActivity;
import com.example.mynews.models.Session;
import com.example.mynews.preferences.Preferences;

public class LogUser {

    public static LogUser currentLogUser;


    private Activity activity;
    private String file = "logUser";
    public LogUser(Activity _activity){
        this.activity = _activity;
    }
    public void login(String _token){
        (new Preferences(this.file, this.activity)).write("token", _token);
    }

    public boolean isLogged(){
        return this.getSession().getToken()!=null;
    }

    public void validatelog(){
        if (!isLogged()){
            Intent intent = new Intent (this.activity, LoginActivity.class);
            this.activity.startActivityForResult(intent, 0);
            this.activity.finish();
        }
    }

    public Session getSession (){
        return new Session((new Preferences(this.file, this.activity).read("token")));
    }

    public void logout(){
        (new Preferences(this.file, this.activity)).remove("token");
    }
}