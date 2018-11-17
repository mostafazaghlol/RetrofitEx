package com.designsapps.retrofitex;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface endpoints {
    @GET("posts")
    Call<List<User>> getUsers();
}
