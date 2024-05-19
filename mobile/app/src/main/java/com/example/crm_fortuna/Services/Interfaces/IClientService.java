package com.example.crm_fortuna.Services.Interfaces;

import com.example.crm_fortuna.Models.ClientModel;
import com.example.crm_fortuna.Services.Reverse_HandShakeError;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IClientService {
    @GET("api/client/name/{client_name}")
    Call<ClientModel[]> getClientByName(@Path("client_name") String client_name);

    @GET("api/client/{client_id}")
    Call<ClientModel> getClientById(@Path("client_id") String client_id);

    @GET("api/client")
    Call<ClientModel[]> getAllClients();

    @PUT("api/client/{client_id}")
    Call<Response<ClientModel>> updateClient(@Path("client_id") String client_id, @Body ClientModel client);


    public Reverse_HandShakeError reverse_handS = new Reverse_HandShakeError();
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://192.168.1.11:5001")
            .addConverterFactory(GsonConverterFactory.create())
            .client(reverse_handS.getUnsafeOkHttpClient().build())
            .build();
}
