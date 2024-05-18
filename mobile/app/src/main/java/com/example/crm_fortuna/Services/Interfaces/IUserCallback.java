package com.example.crm_fortuna.Services.Interfaces;

import com.example.crm_fortuna.Models.UserModel;

public interface IUserCallback {
        void onUserReceived(UserModel user);
        void onFalure(String errorMessage);
}

