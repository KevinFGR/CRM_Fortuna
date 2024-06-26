package com.example.crm_fortuna;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crm_fortuna.Models.ClientModel;
import com.example.crm_fortuna.Services.ClientService;
import com.example.crm_fortuna.Services.Interfaces.IClientCallback;
import com.example.crm_fortuna.Helpers.GetClientOnView;

public class UpdateActivity extends AppCompatActivity {
    Button btn_cancel, btn_update;
    Spinner sp_contrP, sp_product;
    EditText txt_name, txt_email, txt_phone, txt_cpf_cnpj, txt_positions, txt_channels, txt_price, txt_description;
    String client_id, name,email, phone, cpf_cnpj, product, contrP, positions, channels, price, description;
    ClientService clientService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        clientService = new ClientService();

        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ goToMainActivity(); }
        });
        fillInputs();

        btn_update = (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("btn_update","btn_update clicked");
                btn_update.setText("LOADING ...");
                ClientModel client = GetClientOnView.getClientOnView(txt_name,txt_email,txt_phone,
                                        txt_cpf_cnpj,sp_product, sp_contrP,
                                        txt_positions,txt_channels,txt_price,txt_description);
                updateClient(client_id, client);
            }
        });
    }

    private void fillInputs(){
        // just separating the filling information to make the onCreate method cleaner.
        sp_product = (Spinner) findViewById(R.id.sp_product);
        String[] products_options = getResources().getStringArray(R.array.list_products);
        ArrayAdapter<String> adapter_p = new ArrayAdapter<>(
                this, android.R.layout.simple_gallery_item, products_options);
        sp_product.setAdapter(adapter_p);

        sp_contrP = (Spinner) findViewById(R.id.sp_contract_plan);
        //Taking the list information
        String[] contracted_plans_options = getResources().getStringArray(R.array.list_contracted_plans);
        //Passing the list items to the spinner
        ArrayAdapter<String> adapter_cp =new ArrayAdapter<String>(
                this, android.R.layout.simple_gallery_item, contracted_plans_options);
        sp_contrP.setAdapter(adapter_cp);

        txt_name = (EditText) findViewById(R.id.txt_name);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_phone = (EditText) findViewById(R.id.txt_phone);
        txt_cpf_cnpj = (EditText) findViewById(R.id.txt_cpf_cnpj);
        txt_positions = (EditText) findViewById(R.id.txt_positions);
        txt_channels = (EditText) findViewById(R.id.txt_channels);
        txt_price = (EditText) findViewById(R.id.txt_price);
        txt_description = (EditText) findViewById(R.id.txt_description);

        Intent i = getIntent();
        client_id = i.getStringExtra("CLIENT_ID");
        name = i.getStringExtra("NAME");
        email = i.getStringExtra("EMAIL");
        phone = i.getStringExtra("PHONE");
        cpf_cnpj = i.getStringExtra("CPF_CNPJ");
        product = i.getStringExtra("PRODUCT");
        contrP = i.getStringExtra("CONTRP");
        positions = i.getStringExtra("POSITIONS");
        channels = i.getStringExtra("CHANNELS");
        price = i.getStringExtra("PRICE");
        description = i.getStringExtra("DESCRIPTION");

        txt_name.setText(name);
        txt_email.setText(email);
        txt_phone.setText(phone);
        txt_cpf_cnpj.setText(cpf_cnpj);
        sp_product.setSelection(prodIndex(product));
        sp_contrP.setSelection(contrPIndex(contrP));
        txt_positions.setText(positions);
        txt_channels.setText(channels);
        txt_price.setText(price);
        txt_description.setText(description);
    }
    private int prodIndex(String Pname){
        switch (Pname){
            case "Discador": return 0;
            case "URA Ativa": return 1;
            case "URA Reversa": return 2;
            default : return 3;
        }
    }
    private int contrPIndex(String Cname){
        switch (Cname){
            case "Tarifado": return 0;
            case "Ilimitado": return 1;
            default: return 2;
        }
    }
    private void goToMainActivity(){
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
    private void updateClient(String id, ClientModel client){
        clientService.updateClient(id, client, new IClientCallback() {
            @Override
            public void onClientReceived(ClientModel client) {
                    showToast("The client was successfuly updated");

                Log.d("updateClient","onClientReceived");
                goToMainActivity();
            }

            @Override
            public void onClientsReceived(ClientModel[] clients) {
                // Only implemented when the method returns a list of clients
            }

            @Override
            public void onFalure(String errorMessage) {

                Log.d("updateClient(id, client)","onFailure");
                showToast(errorMessage);
            }
        });
    }
    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}