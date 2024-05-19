package com.example.crm_fortuna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crm_fortuna.Helpers.GetClientOnView;
import com.example.crm_fortuna.Models.ClientModel;
import com.example.crm_fortuna.Services.ClientService;
import com.example.crm_fortuna.Services.Interfaces.IClientCallback;

public class PostActivity extends AppCompatActivity {
    ClientService clientService;
    Button btn_save, btn_cancel;
    Spinner sp_contrP, sp_product;
    EditText txt_name, txt_email, txt_phone, txt_cpf_cnpj, txt_positions, txt_channels, txt_price, txt_description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        clientService = new ClientService();

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goToMainActivity(); }
        });

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

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_save.setText("LOADING ...");
                ClientModel client = GetClientOnView
                        .getClientOnView(txt_name, txt_email, txt_phone,
                                        txt_cpf_cnpj, sp_product, sp_contrP,
                                        txt_positions, txt_channels, txt_price,
                                        txt_description);
                postClient(client);
            }
        });
    }
    private void postClient(ClientModel client){
        clientService.postClient(client, new IClientCallback() {
            @Override
            public void onClientReceived(ClientModel client) {
                //Log.d("postClient","onCLientReceived");
                showToast("Client successfuly created!");
                goToMainActivity();
            }

            @Override
            public void onClientsReceived(ClientModel[] clients) {
                // Only implemented when the method returns a list of clients.
            }

            @Override
            public void onFalure(String errorMessage) {
                //Log.d("postClient", "onFalure");
                String message = "Something went wrong trying to add new client \n"+ errorMessage;
                showToast(message);
                btn_save.setText("Save");
            }
        });
    }
    private void goToMainActivity(){
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
    private void showToast(String message){
        Toast.makeText(PostActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}