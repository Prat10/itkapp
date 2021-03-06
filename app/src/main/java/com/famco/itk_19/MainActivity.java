package com.famco.itk_19;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView web1;
    ImageView web2;
    ImageView web3;
    ImageView help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web1=findViewById(R.id.web1);
        web1.setOnClickListener(this);
        web2=findViewById(R.id.web2);
        web2.setOnClickListener(this);
        web3=findViewById(R.id.web3);
        web3.setOnClickListener(this);
        help=findViewById(R.id.help);
        help.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        if(view==web1){
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://itkorba.ac.in/"));
            startActivity(intent);
     }
       else if(view==web2){
           Intent intent=new Intent(MainActivity.this,Login.class);
           startActivity(intent);
        }
       else if(view==web3){
           Intent intent=new Intent(MainActivity.this,Mechanical.class);
           startActivity(intent);
        }
        else if(view==help){
            Toast.makeText(MainActivity.this,"Soon Available",Toast.LENGTH_SHORT).show();
        }
    }
    }


