package com.famco.itk_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Electrical extends AppCompatActivity implements View.OnClickListener {
    Button syllabus, sy2;
    ImageView nptl;
    ImageView ndl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrical);
        syllabus = findViewById(R.id.syl);
        nptl = findViewById(R.id.nptl);
        ndl = findViewById(R.id.ndl);
        nptl.setOnClickListener(this);
        ndl.setOnClickListener(this);
        sy2 = findViewById(R.id.sy2);
        sy2.setOnClickListener(this);
        syllabus.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == syllabus) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://csvtu.ac.in/ew/download/b-tech-5th-semester-5/?wpdmdl=14364&refresh=616e41f21403d1634615794"));
            startActivity(intent);
        } else if (view == sy2) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://open.umn.edu/opentextbooks/subjects/computer-science-information-systems"));
            startActivity(intent);
        } else if (view == nptl) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://nptel.ac.in/"));
            startActivity(intent);
        } else if (view == ndl) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://ndl.iitkgp.ac.in/"));
            startActivity(intent);
        }

    }
}






