package com.example.crm_fortuna.Models;

public class UserModel {
    private float Id;
    private String Name;
    private String UserPosition;
    private String Login;
    private String Password;

    public float getId(){ return this.Id; }
    public void setId(float id) { this.Id = id; }

    public String getName(){ return this.Name; }
    public void setName(String name){ this.Name = name; }

    public String getUserPosition(){ return this.UserPosition; }
    public void setUserPosition(String userPosition){ this.UserPosition = userPosition; }

    public String getLogin(){ return this.Login; }
    public void setLogin(String login){ this.Login = login; }

    public String getPassword(){ return this.Password; }
    public void setPassword(String password) { this.Password = password; }


}
