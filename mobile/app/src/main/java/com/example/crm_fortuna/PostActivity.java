package com.example.crm_fortuna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PostActivity extends AppCompatActivity {

    Button btn_save;
    Button btn_cancel;

    Spinner sp_product;
    Spinner sp_contracted_plan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

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

        sp_contracted_plan = (Spinner) findViewById(R.id.sp_contract_plan);
        //Taking the list information
        String[] contracted_plans_options = getResources().getStringArray(R.array.list_contracted_plans);
        //Passing the list items to the spinner
        ArrayAdapter<String> adapter_cp =new ArrayAdapter<String>(
                this, android.R.layout.simple_gallery_item, contracted_plans_options);
        sp_contracted_plan.setAdapter(adapter_cp);
    }
    private void goToMainActivity(){
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
}