package com.example.mynews.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mynews.MainActivity;
import com.example.mynews.R;
import com.example.mynews.login.LogUser;
import com.example.mynews.models.Session;
import com.example.mynews.services.mynews.MyNewsRetrofit;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private MyNewsRetrofit retrofit;
    private LogUser logUser;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getSupportActionBar().hide();
        this.retrofit = new MyNewsRetrofit();
    }

    public void showUserRegisterForm(View view){
        Intent intent = new Intent (this, RegisterUserActivity.class);
        this.startActivity(intent);
    }

    public void login(View _view){
        String username = ((EditText)this.findViewById(R.id.username_input)).getText().toString();
        String password = ((EditText)this.findViewById(R.id.password_input)).getText().toString();
        final TextView error = (TextView) findViewById(R.id.login_error);
        logUser = new LogUser(this);
        intent = new Intent (this, MainActivity.class);
        this.retrofit.login(username, password).enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                if (response.isSuccessful()){
                    Session session = response.body();
                    LogUser.currentLogUser.login(session.getToken());
                    startActivityForResult(intent, 0);
                    finish();
                } else {
                    try {
                        JSONObject errorObject = new JSONObject(response.errorBody().string());
                        String error_response = "";
                        for (Iterator<String> it = errorObject.keys(); it.hasNext(); ) {
                            String str = it.next();
                            error_response += str + ": " + errorObject.getJSONArray(str).get(0) + "\n";
                        }
                        error.setText(error_response);
                    } catch (IOException | JSONException e) {
                        error.setText(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                error.setText(t.getMessage());
            }
        });
    }


}