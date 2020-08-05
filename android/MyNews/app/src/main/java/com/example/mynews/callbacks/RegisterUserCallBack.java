package com.example.mynews.callbacks;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.example.mynews.models.User;
import com.example.mynews.toast.ShowToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserCallBack implements Callback<User> {
    private Context context;
    private Activity activity;
    private TextView errorsTextView;

    public RegisterUserCallBack(Context context, Activity activity, TextView errors){
        this.context = context;
        this.activity = activity;
        this.errorsTextView = errors;
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if (response.isSuccessful()){
            ShowToast.show(this.context, "Usuario registrado exitosamente.");
            this.activity.finish();
        } else {
            try {
                JSONObject errorObject = new JSONObject(response.errorBody().string());
                String error_response = "";
                for (Iterator<String> it = errorObject.keys(); it.hasNext(); ) {
                    String str = it.next();
                    error_response += str + ": " + errorObject.getJSONArray(str).get(0) + "\n";
                }
                errorsTextView.setText(error_response);
            } catch (IOException | JSONException e) {
                errorsTextView.setText(e.getMessage());
            }
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        ShowToast.show(this.context, "No se pudo establecer conexión.");
    }
}
