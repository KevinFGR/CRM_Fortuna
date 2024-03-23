package com.example.crm_fortuna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText txt_search;
    Button btn_search;
    Button btn_post;
    LinearLayout list_element1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_search = (EditText) findViewById(R.id.txt_search);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_post = (Button) findViewById(R.id.btn_post);
        list_element1 = (LinearLayout)  findViewById(R.id.list_element1);


        list_element1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetailActivity();
            }
        });

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goToPostActivity(); }
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