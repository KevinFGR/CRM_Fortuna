package com.example.crm_fortuna.Services;

import com.example.crm_fortuna.Models.UserModel;
import com.example.crm_fortuna.Services.Interfaces.ICallBack;
import com.example.crm_fortuna.Services.Interfaces.IUserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUser {

    public IUserService iUserService = IUserService.retrofit.create(IUserService.class);

    public void getUserById(String user_login, final ICallBack callback) {
        Call<UserModel> call = iUserService.getUserByLogin(user_login);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    UserModel users = response.body();

                    callback.onUserReceived(users);
                }else{
                    callback.onFalure("Error getting user");
                }
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable throwable) {
                callback.onFalure(
                        throwable.getMessage()
                );
            }
        });
    }
}