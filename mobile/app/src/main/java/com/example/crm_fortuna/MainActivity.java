package com.example.crm_fortuna;

import static com.google.android.material.internal.ViewUtils.dpToPx;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crm_fortuna.Models.ClientModel;
import com.example.crm_fortuna.Services.ClientService;
import com.example.crm_fortuna.Services.Interfaces.IClientCallback;



public class MainActivity extends AppCompatActivity{
    EditText txt_search;
    Button btn_search, btn_post;
    LinearLayout list_element1;
    TextView name_api;
    LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = (LinearLayout) findViewById(R.id.content);

        txt_search = (EditText) findViewById(R.id.txt_search);
        btn_search = (Button) findViewById(R.id.btn_search);

        list_element1 = (LinearLayout)  findViewById(R.id.list_element1);
        list_element1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetailActivity();
            }
        });

        btn_post = (Button) findViewById(R.id.btn_post);
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goToPostActivity(); }
        });

        name_api = (TextView) findViewById(R.id.name_api);

        ClientService clientService = new ClientService();
        clientService.getClientByName("e", new IClientCallback() {
            @Override
            public void onClientsReceived(ClientModel[] clients) {
                if(clients == null){
                    Toast.makeText(MainActivity.this, "Clients is null", Toast.LENGTH_SHORT).show();
                }
                else if(clients[0].getName() == null){
                    Toast.makeText(MainActivity.this, "name is null", Toast.LENGTH_SHORT).show();
                }
                else{
                    for (ClientModel client:clients) {

                        String name = client.getName();
                        String prod = client.getProduct();
                        String contrP = client.getContracted_plan();
                        String position = String.valueOf(client.getPositions());

                        createDivClient(name, prod, contrP, position);
                    }
                }
            }

            @Override
            public void onClientReceived(ClientModel client) {

            }

            @Override
            public void onFalure(String errorMessage) {
                name_api.setText(errorMessage);
            }
        });
    }
    private void goToDetailActivity(){
        Intent detailActivity = new Intent(this, DetailActivity.class);
        startActivity(detailActivity);
    }
    private void goToPostActivity(){
        Intent postActivity = new Intent(this, PostActivity.class);
        startActivity(postActivity);
    }
    private void createDivClient(String name, String product, String contrP, String positions){
        // Creating the linear layout that will have inside all the client's informations
        LinearLayout clientDiv = new LinearLayout(this);
        LinearLayout.LayoutParams clientDivP = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        clientDivP.setMargins(dpToPx(30),dpToPx( 5), dpToPx(30), dpToPx(5));
        clientDiv.setLayoutParams(clientDivP);
        clientDiv.setOrientation(LinearLayout.VERTICAL);
        clientDiv.setPadding(dpToPx(20),dpToPx(20),dpToPx(20),dpToPx(20));
        clientDiv.setBackgroundColor(ContextCompat.getColor(this,R.color.gray_light));

        // Creating the TextView for the Client Name
        TextView txtName = new TextView(this);
        LinearLayout.LayoutParams txtNameP = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        txtNameP.setMargins(0,0,0,dpToPx(10));
        txtName.setText(name);
        txtName.setTextSize(dpToPx(12));
        txtName.setTypeface(null, Typeface.BOLD);

        LinearLayout prodLayout = createHorizontalLayout("Product: ", product);
        LinearLayout contrPLayout = createHorizontalLayout("Contracted Plan: ", contrP);
        LinearLayout positionLayout = createHorizontalLayout("Positions: ", positions);

        clientDiv.addView(txtName);
        clientDiv.addView(prodLayout);
        clientDiv.addView(contrPLayout);
        clientDiv.addView(positionLayout);
        content.addView(clientDiv);

    }
    private LinearLayout createHorizontalLayout(String lbl, String value){
        // Creating the Horizontal layout for product informations
        LinearLayout horizontalLayout = new LinearLayout(this);
        horizontalLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Creating the label for product:
        TextView lblItem = new TextView(this);
        lblItem.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        lblItem.setText(lbl);
        lblItem.setTypeface(null, Typeface.BOLD);

        // creating the TextView for the product
        TextView txtValue = new TextView(this);
        txtValue.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        txtValue.setText(value);

        horizontalLayout.addView(lblItem);
        horizontalLayout.addView(txtValue);

        return horizontalLayout;
    }
    private int dpToPx(int dp){
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp*density);
    }
}