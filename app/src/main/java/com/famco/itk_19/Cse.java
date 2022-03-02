package com.famco.itk_19;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Cse extends AppCompatActivity implements View.OnClickListener {
    ImageButton image;
    private FirebaseUser user;
    ImageView study;
    ImageView web1;
    ImageView fee;
    ImageView attend;
    private DatabaseReference reference;
    private String userID;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cse);
        image = findViewById(R.id.imageButton);
        image.setOnClickListener(this);
        web1=findViewById(R.id.web1);
        web1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://csvtu.ac.in/ew/result-reg-supply/"));
                startActivity(intent);
            }
        });
        study = findViewById(R.id.study);
        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cse.this, Electrical.class);
                startActivity(intent);
            }
        });
        fee = findViewById(R.id.fee);
        fee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cse.this, Fee.class);
                startActivity(intent);
            }
        });
        attend=findViewById(R.id.attend);
        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cse.this, Attendence.class);
                startActivity(intent);
            }
        });
        user = mAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Student");
        userID = user.getUid();
        final TextView email1 = (TextView) findViewById(R.id.email);
        final TextView it = (TextView) findViewById(R.id.item);
        final TextView it1 = (TextView) findViewById(R.id.item1);
        final TextView name1 = (TextView) findViewById(R.id.name);

        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student userprofile = snapshot.getValue(Student.class);
                if (userprofile != null) {
                    String email = userprofile.email;
                    String item = userprofile.item;
                    String item1 = userprofile.item1;
                    String name = userprofile.name;

                    email1.setText("Email: " + email);
                    it.setText("Branch: " + item);
                    it1.setText("Sem:" + item1);
                    name1.setText("Name: " + name);
                }
            }

            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Cse.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick (View v) {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            pickImage();
        else
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                pickImage();
            else
                Toast.makeText(this, "please allow the Storage permission", Toast.LENGTH_SHORT).show();
        }

    }

    private void pickImage() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent chooser = Intent.createChooser(galleryIntent, "Select Image");
        startActivityForResult(chooser, 123);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            image.setImageURI(uri);

        }
    }
}