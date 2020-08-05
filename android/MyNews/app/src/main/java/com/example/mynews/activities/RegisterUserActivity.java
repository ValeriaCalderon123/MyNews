package com.example.mynews.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mynews.R;
import com.example.mynews.callbacks.RegisterUserCallBack;
import com.example.mynews.services.mynews.MyNewsRetrofit;

import java.util.regex.Pattern;

public class RegisterUserActivity extends AppCompatActivity {

    private MyNewsRetrofit retrofit;
    private TextView errorTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        this.getSupportActionBar().hide();
        this.retrofit = new MyNewsRetrofit();
        this.errorTextView = (TextView) this.findViewById(R.id.user_register_error);
    }

    public void registerUser(View view){
        String username, last_name, first_name, email, password;
        username = ((EditText) this.findViewById(R.id.username) ).getText().toString();
        last_name = ((EditText) this.findViewById(R.id.last_name) ).getText().toString();
        first_name = ((EditText) this.findViewById(R.id.first_name) ).getText().toString();
        email = ((EditText) this.findViewById(R.id.email) ).getText().toString();
        password = ((EditText) this.findViewById(R.id.password) ).getText().toString();

        if (validateUserData(username, last_name, first_name, email, password)){
            this.retrofit.registerUser(username, last_name, first_name, email, password)
                    .enqueue(new RegisterUserCallBack(
                            this.getApplicationContext(),
                            this,
                            this.errorTextView));

        } else {
            errorTextView.setText("Por favor llene los campos correctamente.");
        }

    }

    public void cancel(View view){
        this.finish();
    }

    public boolean validateUserData(String username, String last_name, String first_name, String email, String password){
        String text_pattern = "^[a-zA-Z0-9].*$";
        String email_pattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$";
        Pattern r_text = Pattern.compile(text_pattern);
        Pattern r_email = Pattern.compile(email_pattern);
        if (!r_text.matcher(username).find()){
            return false;
        }
        if (!r_text.matcher(last_name).find()){
            return false;
        }
        if (!r_text.matcher(first_name).find()){
            return false;
        }
        if (!r_text.matcher(password).find()){
            return false;
        }
        if (!r_email.matcher(email).find()){
            return false;
        }
        return true;
    }
}