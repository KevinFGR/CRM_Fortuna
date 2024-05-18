package com.example.crm_fortuna.Services.Interfaces;

import com.example.crm_fortuna.Models.ClientModel;

public interface IClientCallback {
        void onClientReceived(ClientModel client);
        void onClientsReceived(ClientModel[] clients);
        void onFalure(String errorMessage);
}

