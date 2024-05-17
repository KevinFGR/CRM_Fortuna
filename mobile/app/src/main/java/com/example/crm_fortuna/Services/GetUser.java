package com.example.crm_fortuna.Services;

import com.example.crm_fortuna.Models.UserModel;
import com.example.crm_fortuna.Services.Interfaces.ICallBack;
import com.example.crm_fortuna.Services.Interfaces.IUserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUser {

    private String login;
    public UserModel user;

    public GetUser(String login){
        this.login = login;
    }

    public IUserService iUserService = IUserService.retrofit.create(IUserService.class);

    public void getUserByLogin(final ICallBack callback) {
        Call<UserModel> call = iUserService.getUserByLogin(this.login);
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