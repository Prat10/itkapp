package com.famco.itk_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class TakeAttend extends AppCompatActivity {
 Button atd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attend);
        verifyStoragePermission(this);
        atd =findViewById(R.id.atd);
        atd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeScreenShot(getWindow().getDecorView().getRootView(),"camera");
                Toast.makeText(TakeAttend.this,"Attendance Taken Successfully",Toast.LENGTH_SHORT).show();
            }
        });

    }
    protected static File takeScreenShot(View view,String fileName){
        Date date =new Date();
        CharSequence format = DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);
        try{
            String dirPath= Environment.getExternalStorageDirectory().toString()+"/Camera";
            File fileDir=new File(dirPath);
            if (!fileDir.exists()){
                boolean mkdir=fileDir.mkdir();
            }
            String path =dirPath+"/" +fileName+"-"+format+".jpg";
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap=Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            File imageFile=new File(path);

            FileOutputStream fileOutputStream =new FileOutputStream(imageFile);
            int quality=100;
            bitmap.compress(Bitmap.CompressFormat.JPEG,quality,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return imageFile;

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static void verifyStoragePermission(Activity activity){
        int permission = ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission!= PackageManager.PERMISSION_GRANTED){
         ActivityCompat.requestPermissions(activity,PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
        }
    }
}