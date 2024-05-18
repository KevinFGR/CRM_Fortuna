package com.example.crm_fortuna.Models;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("userPosition")
    private String userPosition;
    @SerializedName("login")
    private String login;
    @SerializedName("password")
    private String password;

    public float getId(){ return this.id; }
    public void setId(int id) { this.id = id; }

    public String getName(){ return this.name; }
    public void setName(String name){ this.name = name; }

    public String getUserPosition(){ return this.userPosition; }
    public void setUserPosition(String userPosition){ this.userPosition = userPosition; }

    public String getLogin(){ return this.login; }
    public void setLogin(String login){ this.login = login; }

    public String getPassword(){ return this.password; }
    public void setPassword(String password) { this.password = password; }


}
