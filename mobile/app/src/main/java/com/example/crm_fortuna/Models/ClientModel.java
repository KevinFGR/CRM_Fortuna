package com.example.crm_fortuna.Models;

public class ClientModel {
    public ClientModel(){ }

    private int Id;
    private String Name;
    private String Email;
    private String Phone;
    private String CPF_CNPJ;
    private String Product;
    private String Contracted_plan;
    private int Channels;
    private float Price;
    private String Description;


    public int getId() { return this.Id; }
    public void setId(int id) { this.Id = id; }

    public String getName() { return this.Name; }
    public void setName(String name) { this.Name = name; }

    public String getEmail() { return this.Email; }
    public void setEmail(String email) { this.Email = email; }

    public String getPhone() { return this.Phone; }
    public void setPhone(String phone) { this.Phone = phone; }

    public String getCPF_CNPJ() { return this.CPF_CNPJ; }
    public void setCPF_CNPJ(String cpf_cnpj) { this.CPF_CNPJ = cpf_cnpj; }

    public String getProduct() { return this.Product; }
    public void setProduct(String product) { this.Product = product; }

    public String getContracted_plan(){ return this.Contracted_plan; }
    public void setContracted_plan(String ct){ this.Contracted_plan = ct;}

    public int getChannels() { return this.Channels; }
    public void setChannels(int channels) { this.Channels = channels; }

    public float getPrice(){ return this.Price; }
    public void setPrice(float price) { this.Price = price; }

    public String getDescription() { return this.Description; }
    public void setDescription(String description) { this.Description = description; }
}
