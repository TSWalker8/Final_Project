package com.uottawa.michaelhuynh.seg2105project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    CheckBox admin, serviceProvider, homeOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        admin = (CheckBox) findViewById(R.id.cbAdmin);
        admin.setOnClickListener(this);
        serviceProvider = (CheckBox) findViewById(R.id.cbService);
        serviceProvider.setOnClickListener(this);
        homeOwner = (CheckBox) findViewById(R.id.cbHome);
        homeOwner.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

        switch (view.getId()){
            case R.id.cbAdmin:
                if(admin.isChecked())
                    Toast.makeText(getApplicationContext(), "Admin", Toast.LENGTH_LONG).show();
                break;
            case R.id.cbService:
                if(serviceProvider.isChecked())
                    Toast.makeText(getApplicationContext(), "Service Provider", Toast.LENGTH_LONG).show();
                break;
            case R.id.cbHome:
                if(homeOwner.isChecked())
                    Toast.makeText(getApplicationContext(), "Homeowner", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
