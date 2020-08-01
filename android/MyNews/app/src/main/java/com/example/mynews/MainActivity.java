package com.example.mynews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mynews.login.LogUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUser.currentLogUser = new LogUser(this);
        LogUser.currentLogUser.validatelog();

    }
}