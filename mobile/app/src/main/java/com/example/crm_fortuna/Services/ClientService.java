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

        public void getClientById(String client_id, IClientCallback callback) {

            Call<ClientModel> call = iClientService.getClientById(client_id);
            call.enqueue(new Callback<ClientModel>() {
                @Override
                public void onResponse(Call<ClientModel> call, Response<ClientModel> response) {
                    if(response.isSuccessful()){
                        ClientModel client = response.body();
                        callback.onClientReceived(client);
                    }else{
                        callback.onFalure("Something went wrong trying to get clients by id");
                    }
                }

                @Override
                public void onFailure(Call<ClientModel> call, Throwable throwable) {
                    callback.onFalure(throwable.getMessage());
                }
            });

        }

        public void getAllClients(IClientCallback callback) {
            Call<ClientModel[]> call = iClientService.getAllClients();
            call.enqueue(new Callback<ClientModel[]>() {
                @Override
                public void onResponse(Call<ClientModel[]> call, Response<ClientModel[]> response) {
                    if(response.isSuccessful()){
                        ClientModel[] clients = response.body();
                        callback.onClientsReceived(clients);
                    }else{
                        callback.onFalure("Something wrong occurred trying get all clients");
                    }
                }

                @Override
                public void onFailure(Call<ClientModel[]> call, Throwable throwable) {
                    callback.onFalure(throwable.getMessage());
                }
            });
        }

        public void updateClient(String id, ClientModel client, IClientCallback callback){
            Call<Response<ClientModel>> call = iClientService.updateClient(id,client);

            call.enqueue(new Callback<Response<ClientModel>>() {
                @Override
                public void onResponse(Call<Response<ClientModel>> call, Response<Response<ClientModel>> response) {
                    if(response.isSuccessful()) {
                        ClientModel client = response.body().body();
                        callback.onClientReceived(client);
                    }else{
                        callback.onFalure("Something went wrong trying to update client");
                    }
                }
                @Override
                public void onFailure(Call<Response<ClientModel>> call, Throwable throwable) {
                    callback.onFalure(throwable.getMessage());
                }
            });
        }

}
