package com.example.crm_fortuna.Services;

import com.example.crm_fortuna.Models.ClientModel;
import com.example.crm_fortuna.Models.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class GetTest {
    private interface RequestUser{
        // @GET("api/client/{user_id}")
        // Call<UserModel> getUser(@Path("user_id") String user_id);

        @GET("api/user/")
        Call<UserModel[]> getUser();
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://localhost:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private RequestUser requestUser = retrofit.create(RequestUser.class);

    public String getAllUser(){
        Call<UserModel[]> user = requestUser.getUser();
        return user.toString();
    }
}