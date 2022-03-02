package com.famco.itk_19;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class Mechanical extends AppCompatActivity implements View.OnClickListener{

    EditText inputEmail, inputPassword, inputName;
    Button register;
    TextView signin;
    Spinner branch;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanical);
        mAuth = FirebaseAuth.getInstance();
        signin = findViewById(R.id.lnkLogin);
        branch=findViewById(R.id.spinner1);
        signin.setOnClickListener(this);
        register = findViewById(R.id.btnRegister);
        inputEmail = (EditText) findViewById(R.id.txtEmail);
        inputPassword = (EditText) findViewById(R.id.txtPwd);
        inputName = findViewById(R.id.txtName);
        databaseReference=FirebaseDatabase.getInstance().getReference("Staff");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=inputEmail.getText().toString().trim();
                String password=inputPassword.getText().toString().trim();
                String name=inputName.getText().toString().trim();
                String item = branch.getSelectedItem().toString();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)||TextUtils.isEmpty(name)){
                    Toast.makeText(Mechanical.this, "Enter email and password ", Toast.LENGTH_SHORT).show();
                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Mechanical.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Staff information=new Staff(
                                    email,password,name,item
                            );
                            FirebaseDatabase.getInstance().getReference("Staff")
                                    .child(mAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    Toast.makeText(Mechanical.this,"Registration completed",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),Homepage.class));
                                }
                            });
                        }
                    }});
            }
        });
    }

    @Override
    public void onClick(View view){
        if(view==signin){
            Intent intent=new Intent(Mechanical.this,Civil.class);
            startActivity(intent);
        }
    }
}