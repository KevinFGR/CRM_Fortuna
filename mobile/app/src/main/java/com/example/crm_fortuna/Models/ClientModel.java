package com.example.crm_fortuna.Models;

import com.google.gson.annotations.SerializedName;

public class ClientModel {
    public ClientModel(){}
    public ClientModel(String name, String email, String phone,
                        String cpf_cnpj, String product, String contrP,
                        int positions, int channels, float price,
                        String description){

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpF_CNPJ = cpf_cnpj;
        this.product = product;
        this.contracted_plan = contrP;
        this.positions = positions;
        this.channels = channels;
        this.price = price;
        this.description = description;
    }

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("cpF_CNPJ")
    private String cpF_CNPJ;
    @SerializedName("product")
    private String product;
    @SerializedName("contracted_plan")
    private String contracted_plan;
    @SerializedName("positions")
    private int positions;
    @SerializedName("channels")
    private int channels;
    @SerializedName("price")
    private float price;
    @SerializedName("description")
    private String description;


    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return this.phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCPF_CNPJ() { return this.cpF_CNPJ; }
    public void setCPF_CNPJ(String cpf_cnpj) { this.cpF_CNPJ = cpf_cnpj; }

    public String getProduct() { return this.product; }
    public void setProduct(String product) { this.product = product; }

    public String getContracted_plan(){ return this.contracted_plan; }
    public void setContracted_plan(String ct){ this.contracted_plan = ct;}

    public int getPositions(){ return this.positions; }
    public void setPositions(int pos){ this.positions = pos;}

    public int getChannels() { return this.channels; }
    public void setChannels(int channels) { this.channels = channels; }

    public float getPrice(){ return this.price; }
    public void setPrice(float price) { this.price = price; }

    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }
}
