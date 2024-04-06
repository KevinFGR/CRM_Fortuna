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
    public interface GetUserCallBack{
        void onUserReceived(String users);
        void onFalure(String errorMessage);
    }

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

    public void getAllUser(final GetUserCallBack callback) {
        Call<UserModel[]> call = requestUser.getUser();
        call.enqueue(new Callback<UserModel[]>() {
            @Override
            public void onResponse(Call<UserModel[]> call, Response<UserModel[]> response) {
                if (response.isSuccessful()) {
                    UserModel[] users = response.body();
                    callback.onUserReceived(users.toString());
                }else{
                    callback.onFalure("Error getting users");
                }
            }
            @Override
            public void onFailure(Call<UserModel[]> call, Throwable throwable) {
                callback.onFalure(
                        throwable.getMessage());
            }
        });
    }
}