package com.famco.itk_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Attendence extends AppCompatActivity {
    Button a11,a12,a13,a24,a26;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
       final String num="+919425578735";
       final String num1="+919039048735";
       final String num2="+918349060817";
       final String text="Present";
        a11=findViewById(R.id.a11);
        a12=findViewById(R.id.a12);
        a12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean installed=isAppInstalled("com.whatsapp");
                if(installed){
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+num1+"&text="+text));
                    startActivity(intent);
                }
            }
        });
        a13=findViewById(R.id.a13);
        a13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean installed=isAppInstalled("com.whatsapp");
                if(installed){
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+num2+"&text="+text));
                    startActivity(intent);
                }
            }
        });
        a24=findViewById(R.id.a24);
        a26=findViewById(R.id.a26);
        a11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean installed=isAppInstalled("com.whatsapp");
                if(installed){
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+num+"&text="+text));
                    startActivity(intent);
                }

            }
        });
    }
    private boolean isAppInstalled(String s){
        PackageManager packageManager=getPackageManager();
        boolean is_installed;
        try {
            packageManager.getPackageInfo(s,PackageManager.GET_ACTIVITIES);
            is_installed=true;
        }
        catch (PackageManager.NameNotFoundException e){
            is_installed=false;
            e.printStackTrace();
        }
        return is_installed;
    }

}