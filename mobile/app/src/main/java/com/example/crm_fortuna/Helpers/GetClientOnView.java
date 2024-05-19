package com.example.crm_fortuna.Helpers;

import android.widget.EditText;
import android.widget.Spinner;

import com.example.crm_fortuna.Models.ClientModel;

public class GetClientOnView {
    public static ClientModel getClientOnView(EditText txt_name, EditText txt_email, EditText txt_phone,
                                        EditText txt_cpf_cnpj, Spinner sp_product, Spinner sp_contrP,
                                        EditText txt_positions, EditText txt_channels, EditText txt_price,
                                        EditText txt_description
    ){
        String name = String.valueOf(txt_name.getText());
        String email = String.valueOf(txt_email.getText());
        String phone = String.valueOf(txt_phone.getText());
        String cpf_cnpj = String.valueOf(txt_cpf_cnpj.getText());
        String product = String.valueOf(sp_product.getSelectedItem());
        String contrP = String.valueOf(sp_contrP.getSelectedItem());
        int positions = Integer.parseInt(
                String.valueOf(txt_positions.getText())
        );
        int channels = Integer.parseInt(
                String.valueOf(txt_channels.getText())
        );
        float price = Float.parseFloat(
                String.valueOf(txt_price.getText())
        );
        String description = String.valueOf(txt_description.getText());

        return new ClientModel(
                name, email, phone,
                cpf_cnpj, product, contrP,
                positions, channels, price,
                description
        );
    }
}
