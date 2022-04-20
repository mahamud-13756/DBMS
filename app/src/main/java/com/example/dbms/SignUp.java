package com.example.dbms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private TextInputEditText edtFullName, edtUserName, edtEmail, edtPassword, edtConfirmPassword;
    Button createAccount;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init_();

        mAuth = FirebaseAuth.getInstance();



        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtFullName.getText().toString();
                String uname = edtUserName.getText().toString();
                String email = edtEmail.getText().toString();
                String pass = edtPassword.getText().toString();
                String cpass = edtConfirmPassword.getText().toString();

                if (TextUtils.isEmpty(name)){
                    edtFullName.setError("required");
                    return;
                }
                if (TextUtils.isEmpty(uname)){
                    edtUserName.setError("required");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    edtEmail.setError("required");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    edtPassword.setError("required");
                    return;
                }
                if (TextUtils.isEmpty(cpass)){
                    edtConfirmPassword.setError("required");
                    return;
                }

                if(!pass.equals(cpass)){
                    Toast.makeText(SignUp.this, "Password Mismatch",Toast.LENGTH_SHORT).show();
                    return;

                }


                createUserAccount(email,pass);


            }
        });
    }

    private void createUserAccount(String email, String pass) {
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(SignUp.this, "Successful", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(SignUp.this,MainActivity.class));

                }else{
                    Log.e("Reg.",task.getException().getMessage());
                    Toast.makeText(SignUp.this, "Something went Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void init_() {
        edtFullName = findViewById(R.id.edtFullName);
        edtUserName = findViewById(R.id.edtUserName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        createAccount = findViewById(R.id.go);

    }
}