package com.designsapps.retrofitex;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class controller implements Callback<List<User>> {

    static final String Base_Url = "https://jsonplaceholder.typicode.com/";
    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        endpoints gerritAPI = retrofit.create(endpoints.class);

        Call<List<User>> call = gerritAPI.getUsers();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if(response.isSuccessful()) {
            List<User> changesList = response.body();
            for(int i=0;i<changesList.size();i++){
                Log.e("num"+i,changesList.get(i).getBody());
                Log.e("num"+i,changesList.get(i).getTitle());
                Log.e("num"+i,changesList.get(i).getId().toString());
            }
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        t.printStackTrace();
    }
}
