package com.example.crm_fortuna.Services.Interfaces;

import com.example.crm_fortuna.Models.ClientModel;

public interface IDeleteCallback {
        void onClientDeleted(boolean deleted);
        void onFalure(String errorMessage);
}

