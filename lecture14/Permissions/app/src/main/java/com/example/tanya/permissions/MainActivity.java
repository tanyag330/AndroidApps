package com.example.tanya.permissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN_ACTIVITY";

    String [] reqPermission = new String []{

            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    static int REQUEST_CODE_STORAGE_PERM = 445;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnWriteFile = (Button) findViewById(R.id.btn_write_file);
        btnWriteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasWritePerm()){
                    writeMyFile();
                }
            }
        });

        //if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
        int permissionStatus = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
/*
            switch (permissionStatus){
                case(PackageManager.PERMISSION_GRANTED):
                    Log.d(TAG, "onCreate: Permission Granted");
                    break;
                case(PackageManager.PERMISSION_DENIED):
                    Log.d(TAG, "onCreate: Permission Denied");
                    break;
            }
        }*/
        if(permissionStatus == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, reqPermission , REQUEST_CODE_STORAGE_PERM);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_STORAGE_PERM) {
            /*if (permissions.length>0){
                for (String perm : permissions)
                    Log.d(TAG, "onRequestPermissionsResult: " + perm);
            }
            if (grantResults.length>0){
                for (int res : grantResults)
                    Log.d(TAG, "onRequestPermissionsResult: " + res);
            }*/
            if (grantResults.length > 0) {
                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    writeMyFile();
                } else {
                    ask();
                }

            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void ask(){
        ActivityCompat.requestPermissions(this,reqPermission,REQUEST_CODE_STORAGE_PERM);
    }

    private boolean hasWritePerm() {
        return (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED);
    }

    public void writeMyFile(){
        try {
            File myFile = new File(Environment.getExternalStorageDirectory(), "myFile");

            FileOutputStream fOut = new FileOutputStream(myFile);

            fOut.write("welcome to my File".getBytes());
            fOut.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
