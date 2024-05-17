package com.example.crm_fortuna.Services.Interfaces;

import com.example.crm_fortuna.Models.UserModel;

public interface ICallBack{
        void onUserReceived(UserModel users);
        void onFalure(String errorMessage);
}

