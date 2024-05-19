package com.example.crm_fortuna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crm_fortuna.Models.ClientModel;
import com.example.crm_fortuna.Services.ClientService;
import com.example.crm_fortuna.Services.Interfaces.IClientCallback;
import com.example.crm_fortuna.Services.Interfaces.IDeleteCallback;

public class DetailActivity extends AppCompatActivity{
    // update is executing correctly on API but retrofit is returning TimeOut Exception instead of success.
    Button btn_delete, btn_update, btn_back;
    String client_id;
    TextView txt_name,txt_email, txt_phone, txt_cpf_cnpj, txt_product, txt_contrP,txt_positions, txt_channels,txt_price, txt_description;
    String name,email, phone, cpf_cnpj, product, contrP,positions, channels, price, description;

    ClientService clientService = new ClientService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        client_id = intent.getStringExtra("CLIENT_ID");
        name = intent.getStringExtra("NAME");
        email = intent.getStringExtra("EMAIL");
        phone = intent.getStringExtra("PHONE");
        cpf_cnpj = intent.getStringExtra("CPF_CNPJ");
        product = intent.getStringExtra("PRODUCT");
        contrP = intent.getStringExtra("CONTRP");
        positions = intent.getStringExtra("POSITIONS");
        channels = intent.getStringExtra("CHANNELS");
        price = intent.getStringExtra("PRICE");
        description = intent.getStringExtra("DESCRIPTION");

        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_email = (TextView) findViewById(R.id.txt_email);
        txt_phone = (TextView) findViewById(R.id.txt_phone);
        txt_cpf_cnpj = (TextView) findViewById(R.id.txt_cpf_cnpj);
        txt_product = (TextView) findViewById(R.id.txt_product);
        txt_contrP = (TextView) findViewById(R.id.txt_contrP);
        txt_positions = (TextView) findViewById(R.id.txt_positions);
        txt_channels = (TextView) findViewById(R.id.txt_channels);
        txt_price = (TextView) findViewById(R.id.txt_price);
        txt_description = (TextView) findViewById(R.id.txt_description);

        Log.d("client json:",
            "id: "+client_id+
                ", name: "+name+
                ", email: "+email+
                ", phone: "+phone+
                ", cpf_cnpj: "+cpf_cnpj+
                ", product: "+product+
                ", contrP: "+contrP+
                ", positions: "+positions+
                ", channels: "+channels+
                ", price: "+price+
                ", description: "+description);

        txt_name.setText(name);
        txt_email.setText(email);
        txt_phone.setText(phone);
        txt_cpf_cnpj.setText(cpf_cnpj);
        txt_contrP.setText(product);
        txt_product.setText(contrP);
        txt_positions.setText(positions);
        txt_channels.setText(channels);
        txt_price.setText(price);
        txt_description.setText(description);

        if(client_id!=null) {
            //getClientById(client_id);
        }else{
            Toast.makeText(DetailActivity.this,
                    "Client Id is null",
                          Toast.LENGTH_SHORT).show();
        }

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

        btn_update = (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goToUpdateActivity(); }
        });

        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_delete.setText("LOADING ...");
                deleteClient(client_id);
            }
        });
    }

    private void fillClientInformations(){
        txt_name.setText(name);
        txt_email.setText(email);
        txt_phone.setText(phone);
        txt_cpf_cnpj.setText(cpf_cnpj);
        txt_contrP.setText(contrP);
        txt_product.setText(product);
        txt_positions.setText(positions);
        txt_channels.setText(channels);
        txt_price.setText(price);
        txt_description.setText(description);
    }
    private void goToUpdateActivity(){
        Intent updateActivity = new Intent(this, UpdateActivity.class);
        updateActivity.putExtra("CLIENT_ID", client_id);
        updateActivity.putExtra("NAME", name);
        updateActivity.putExtra("EMAIL", email);
        updateActivity.putExtra("PHONE", phone);
        updateActivity.putExtra("CPF_CNPJ",cpf_cnpj);
        updateActivity.putExtra("PRODUCT",product);
        updateActivity.putExtra("CONTRP",contrP);
        updateActivity.putExtra("POSITIONS",positions);
        updateActivity.putExtra("CHANNELS",channels);
        updateActivity.putExtra("PRICE",price);
        updateActivity.putExtra("DESCRIPTION",description);

        startActivity(updateActivity);
    }
    private void getClientById(String id){
        clientService.getClientById(id, new IClientCallback() {
            @Override
            public void onClientReceived(ClientModel client) {
                if(client == null){
                    Toast.makeText(DetailActivity.this, "client is null",Toast.LENGTH_SHORT).show();
                }else if (client.getName() == null) {
                    Toast.makeText(DetailActivity.this, "client information is null", Toast.LENGTH_SHORT).show();
                }else {
                    name = client.getName();
                    email = client.getEmail();
                    phone = client.getPhone();
                    cpf_cnpj = client.getCPF_CNPJ();
                    product = client.getProduct();
                    contrP = client.getContracted_plan();
                    positions = String.valueOf(client.getPositions());
                    channels = String.valueOf(client.getChannels());
                    price = String.valueOf(client.getPrice());
                    description = client.getDescription();

                    fillClientInformations();
                }
            }
            @Override
            public void onClientsReceived(ClientModel[] clients) {
                // Just implemented when the method return a list
            }

            @Override
            public void onFalure(String errorMessage) {
                Toast.makeText(DetailActivity.this, "Something wrong occurred getting client by id", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void deleteClient(String id){
        clientService.deleteClient(client_id, new IDeleteCallback() {
            @Override
            public void onClientDeleted(boolean deleted) {
                Toast.makeText(DetailActivity.this,
                        "Client successfuly deleted.",
                        Toast.LENGTH_SHORT).show();
                goToMainActivity();
            }

            @Override
            public void onFalure(String errorMessage) {
                Toast.makeText(DetailActivity.this,
                        "Something wrong ocurred trying to delete this client.",
                        Toast.LENGTH_SHORT).show();
                btn_delete.setText("DELETE");
            }
        });
    }
    private void goToMainActivity(){
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
}