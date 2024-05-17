package com.example.crm_fortuna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.crm_fortuna.Models.UserModel;
import com.example.crm_fortuna.Services.GetUser;
import com.example.crm_fortuna.Services.Interfaces.ICallBack;


public class MainActivity extends AppCompatActivity{
    EditText txt_search;
    Button btn_search, btn_post;
    LinearLayout list_element1;
    TextView name_api;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        GetUser getUser = new GetUser();
        getUser.getUserById("dev@kevin", new ICallBack() {
            @Override
            public void onUserReceived(UserModel users) {
                if(users == null){
                    name_api.setText("Users Null result");
                }
                else if(users.getName() == null){
                    name_api.setText("name is null");
                }
                else{
                    name_api.setText(users.getUserPosition());
                }
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
}