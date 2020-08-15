package com.example.mynews.onClickListeners;

import android.content.Context;
import android.view.View;

import com.example.mynews.models.Source;
import com.example.mynews.services.mynews.MyNewsRetrofit;
import com.example.mynews.toast.ShowToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalificationListener implements View.OnClickListener {

    private int calification;
    private String uuid;
    private MyNewsRetrofit retrofit;
    private Context context;

    public CalificationListener(int calification, String uuid, MyNewsRetrofit retrofit, Context context) {
        this.calification = calification;
        this.uuid = uuid;
        this.retrofit = retrofit;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        System.out.println(this.retrofit.calificate(this.uuid, this.calification).request().body());
        this.retrofit.calificate(this.uuid, this.calification).enqueue(new Callback<Source>() {
            @Override
            public void onResponse(Call<Source> call, Response<Source> response) {
                if (response.isSuccessful()){
                    ShowToast.show(context, "Gracias por su calificación");
                } else {
                    ShowToast.show(context, "No se puedo hacer su calificación");
                }
            }

            @Override
            public void onFailure(Call<Source> call, Throwable t) {
                ShowToast.show(context, t.getMessage());
            }
        });
    }
}
