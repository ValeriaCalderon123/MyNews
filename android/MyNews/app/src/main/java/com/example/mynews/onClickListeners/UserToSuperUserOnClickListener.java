package com.example.mynews.onClickListeners;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mynews.callbacks.UserToSuperUserCallBack;
import com.example.mynews.fragments.usersAdmin.UserRecyclerViewAdapter;
import com.example.mynews.services.mynews.MyNewsRetrofit;

public class UserToSuperUserOnClickListener implements View.OnClickListener {
    private EditText editText;
    private MyNewsRetrofit retrofit;
    private Context context;
    private UserRecyclerViewAdapter adapter;

    public UserToSuperUserOnClickListener(EditText editText, MyNewsRetrofit retrofit, Context context, UserRecyclerViewAdapter adapter) {
        this.editText = editText;
        this.retrofit = retrofit;
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    public void onClick(View v) {
        String username = editText.getText().toString();
        this.retrofit.userToSuperUser(username).enqueue(new UserToSuperUserCallBack(this.context, this.adapter, this.editText));
    }
}
