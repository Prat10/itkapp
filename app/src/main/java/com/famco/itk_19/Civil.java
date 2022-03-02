package com.famco.itk_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class Civil extends AppCompatActivity implements View.OnClickListener{

    private EditText inputEmail;
    private EditText inputPassword;
    private TextView forget;
    private Button login1;
    private TextView register1;
    private FirebaseAuth mAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civil);
        inputEmail=findViewById(R.id.txtEmail);
        inputPassword=findViewById(R.id.txtPwd);
        login1=findViewById(R.id.btnLogin);
        forget=findViewById(R.id.forgot);
        register1=findViewById(R.id.lnkRegister);
        register1.setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e=inputEmail.getText().toString();
                mAuth.sendPasswordResetEmail(e).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Civil.this,"Send Mail",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Civil.this,"Fail",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=inputEmail.getText().toString();
                String password=inputPassword.getText().toString();
                if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)) {
                    Toast.makeText(Civil.this, "PLease enter Email and Password", Toast.LENGTH_SHORT).show();
                }else {
                    login3(email,password);
                }
            }
        });
    }
    private void login3(String email,String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(Civil.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Civil.this, "login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Civil.this,Homepage.class));
            }
        });

    }
    @Override
    public void onClick(View v){
        if(v==register1){
            Intent intent=new Intent(Civil.this,Mechanical.class);
            startActivity(intent);
        }
    }
}