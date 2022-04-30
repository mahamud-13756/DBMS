package com.example.dbms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    Button callSignUp,logIn;
    private TextInputEditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init1();



        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = username.getText().toString();
                String pass = password.getText().toString();

                if(uName.isEmpty()){
                    username.setError("required");
                    return;
                }
                if(pass.isEmpty()){
                    password.setError("required");
                    return;
                }

                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });
    }

    private void init1(){

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        callSignUp= findViewById(R.id.new_user);
        logIn = findViewById(R.id.logIn);
    }
}