package com.example.crm_fortuna.Services;

import com.example.crm_fortuna.Models.ClientModel;
import com.example.crm_fortuna.Services.Interfaces.IClientService;
import com.example.crm_fortuna.Services.Interfaces.IClientCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientService{
     IClientService iClientService = IClientService.retrofit.create(IClientService.class);

        public void getClientByName(String client_name, IClientCallback callback) {
            Call<ClientModel[]> call = iClientService.getClientByName(client_name);
            call.enqueue(new Callback<ClientModel[]>() {
                @Override
                public void onResponse(Call<ClientModel[]> call, Response<ClientModel[]> response) {
                    if(response.isSuccessful()) {
                        ClientModel[] clients = response.body();

                        callback.onClientsReceived(clients);

                    }else{
                        callback.onFalure("Something went wrong trying to get clients by name");
                    }
                }

                @Override
                public void onFailure(Call<ClientModel[]> call, Throwable throwable) {
                    callback.onFalure(throwable.getMessage());
                }
            });

        }


        public Call<ClientModel> getClientById(String client_id) {
            return null;
        }


        public Call<ClientModel[]> getAllClients() {
            return null;
        }


}
