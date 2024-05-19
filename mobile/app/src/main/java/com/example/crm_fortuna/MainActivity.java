package com.example.crm_fortuna;

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
    ClientService clientService = new ClientService();
    EditText txt_search;
    Button btn_search, btn_post;
    TextView name_api;
    LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = (LinearLayout) findViewById(R.id.content);

        //Just for tests
        name_api = (TextView) findViewById(R.id.name_api);

        txt_search = (EditText) findViewById(R.id.txt_search);
        btn_search = (Button) findViewById(R.id.btn_search);

        btn_post = (Button) findViewById(R.id.btn_post);
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goToPostActivity(); }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_search.setText("LOADING ...");
                String name = txt_search.getText().toString();
                getClientByName(name);
            }
        });
        try {
            getAllClients();
        }catch(Exception e) {
            showToast(e.getMessage());
            try {
                getAllClients();
            }catch(Exception ex){
                showToast(ex.getMessage());
            }
        }
    }
    private void goToDetailActivity(String client_id){
        Intent detailActivity = new Intent(this, DetailActivity.class);
        detailActivity.putExtra("CLIENT_ID", client_id);
        startActivity(detailActivity);
    }
    private void goToPostActivity(){
        Intent postActivity = new Intent(this, PostActivity.class);
        startActivity(postActivity);
    }

    private void getAllClients(){
        clientService.getAllClients(new IClientCallback() {
            @Override
            public void onClientReceived(ClientModel client) {
                // Just implemented when the method return one element
            }

            @Override
            public void onClientsReceived(ClientModel[] clients) {
                if(clients == null){
                    showToast("Clients are null");
                }else if(clients[0].getName() == null){
                    showToast("Clients content are null");
                }else{
                    content.removeAllViews();
                    for (ClientModel client:clients) {

                        String id = String.valueOf(client.getId());
                        String name = client.getName();
                        String prod = client.getProduct();
                        String contrP = client.getContracted_plan();
                        String position = String.valueOf(client.getPositions());

                        createDivClient(id, name, prod, contrP, position);
                    }
                }
            }
            @Override
            public void onFalure(String errorMessage) {
                showToast("Something went wrong trying to get all clients");
            }
        });
    }

    // Get all the clients that contains the name and create the div (LinearLayout) to show then on activity
    private void getClientByName(String name){
        clientService.getClientByName(name, new IClientCallback() {
            @Override
            public void onClientsReceived(ClientModel[] clients) {
                if(clients == null){
                    showToast("Clients is null");
                }
                else if(clients[0].getName() == null){
                    showToast("Client information is null");
                }
                else{
                    content.removeAllViews();
                    for (ClientModel client:clients) {

                        String id = String.valueOf(client.getId());
                        String name = client.getName();
                        String prod = client.getProduct();
                        String contrP = client.getContracted_plan();
                        String position = String.valueOf(client.getPositions());

                        createDivClient(id, name, prod, contrP, position);
                    }
                }
                btn_search.setText("SEARCH");
            }
            @Override
            public void onClientReceived(ClientModel client) {
                // implemented only when the method returns one client instead of a list
            }
            @Override
            public void onFalure(String errorMessage) {
                showToast(errorMessage);
            }
        });
    }

    // create the structure that contains the client information to show on activity
    private void createDivClient(String id, String name, String product, String contrP, String positions){
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
        clientDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetailActivity(id);
            }
        });

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

    // create the structure of the lines (Horizontal linearLayout) inside the client linearLayout
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

    // To transform DP at Pixels, used by structures methods tha was created
    private int dpToPx(int dp){
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp*density);
    }
    private void showToast(String message){
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}