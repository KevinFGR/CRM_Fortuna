package com.example.crm_fortuna.Services;

import com.example.crm_fortuna.Models.UserModel;
import com.example.crm_fortuna.Services.Interfaces.IUserCallback;
import com.example.crm_fortuna.Services.Interfaces.IUserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserService {

    private IUserService iUserService = IUserService.retrofit.create(IUserService.class);

    public void getUserByLogin(String login, final IUserCallback callback) {
        Call<UserModel> call = iUserService.getUserByLogin(login);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    UserModel user = response.body();

                    callback.onUserReceived(user);
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