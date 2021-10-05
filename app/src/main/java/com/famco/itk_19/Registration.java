package com.famco.itk_19;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

 public class Registration extends AppCompatActivity implements View.OnClickListener{
    private EditText inputEmail, inputPassword, inputName;
    private Button register;
    private TextView signin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mAuth = FirebaseAuth.getInstance();
        signin = findViewById(R.id.lnkLogin);
        signin.setOnClickListener(this);
        register = findViewById(R.id.btnRegister);
        inputEmail = (EditText) findViewById(R.id.txtEmail);
        inputPassword = (EditText) findViewById(R.id.txtPwd);
        inputName = findViewById(R.id.txtName);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=inputEmail.getText().toString().trim();
                String password=inputPassword.getText().toString().trim();
                String name=inputName.getText().toString().trim();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)||TextUtils.isEmpty(name)){
                    Toast.makeText(Registration.this, "Enter email and password ", Toast.LENGTH_SHORT).show();
                }
                else{
                    regis(email,password);
                }
            }
        });
    }
    private void regis(String email,String password)
    {
       mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful())
               {
                   Toast.makeText(Registration.this, "Successfully Registered ", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(Registration.this, "Failed", Toast.LENGTH_SHORT).show();
               }
       }});

       }
     @Override
     public void onClick(View view){
         if(view==signin){
             Intent intent=new Intent(Registration.this,Login.class);
             startActivity(intent);
         }
     }
    }


