package com.example.crm_fortuna.Services.Interfaces;

import com.example.crm_fortuna.Models.UserModel;
import com.example.crm_fortuna.Services.Reverse_HandShakeError;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IUserService {
    @GET("api/user/login/{user_login}")
    Call<UserModel> getUserByLogin(@Path("user_login") String user_login);

    public Reverse_HandShakeError reverse_handS = new Reverse_HandShakeError();
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://192.168.1.11:5001")
            .addConverterFactory(GsonConverterFactory.create())
            .client(reverse_handS.getUnsafeOkHttpClient().build())
            .build();
}
