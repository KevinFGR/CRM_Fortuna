package com.example.crm_fortuna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crm_fortuna.Models.UserModel;
import com.example.crm_fortuna.Services.UserService;
import com.example.crm_fortuna.Services.Interfaces.IUserCallback;

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
                btn_login.setText("LOADING ...");

                String login = txt_user.getText().toString();
                String pass = txt_password.getText().toString();

                authentication(login, pass);
            }
        });
    }

    private void authentication(String login, String passw){

        UserService userService = new UserService();
        userService.getUserByLogin(login, new IUserCallback(){
            @Override
            public void onUserReceived(UserModel users) {
                if(users == null){
                    showToast("This user do not exists.");
                    btn_login.setText("LOGIN");
                } else if (users.getPassword().equals(passw)) {
                    goToMainActivity();
                    btn_login.setText("LOGIN");
                }else{
                    showToast("The password do not match");
                    btn_login.setText("LOGIN");
                }
            }

            @Override
            public void onFalure(String errorMessage) {

                showToast("Something went wrong.");
                btn_login.setText("LOGIN");
            }
        });
    }
    private void goToMainActivity () {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
    public void showToast(String content){
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }

}