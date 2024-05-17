package com.example.crm_fortuna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity{

    Button btn_login;
    EditText txt_user;
    EditText txt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.btn_login);
        txt_user = (EditText) findViewById(R.id.txt_user);
        txt_password = (EditText) findViewById(R.id.txt_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = txt_user.getText().toString();
                String pass = txt_password.getText().toString();

                if(authentication(login, pass)){
                    goToMainActivity();
                }else{
                    showToast("This user do not exists or the password do not match.");
                }
            }
        });
    }

    private boolean authentication(String login, String pass){
        return false;
    }
    private void goToMainActivity () {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
    public void showToast(String content){
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }

}