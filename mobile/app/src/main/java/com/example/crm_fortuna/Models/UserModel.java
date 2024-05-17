package com.example.crm_fortuna.Models;

public class UserModel {
    private int id;
    private String name;
    private String userPosition;
    private String login;
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
