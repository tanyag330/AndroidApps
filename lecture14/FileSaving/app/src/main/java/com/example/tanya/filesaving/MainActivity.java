package com.example.tanya.filesaving;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAINAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String fileDirPath = null;
        String cacheDirPath = null;
        try {
            fileDirPath = getFilesDir().getPath();
            cacheDirPath = getCacheDir().getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onCreate:files dir = " + fileDirPath);
        Log.d(TAG, "onCreate:files dir = " + cacheDirPath);

        /* String filename =  "myfile" ;
        String  string  =  "Hello world!" ;
        FileOutputStream outputStream ;


       File myFile = new File(getFilesDir().getPath(), filename);

        if(myFile.canRead()){
            Log.d(TAG, "onCreate: 1.can read file =" + filename);
        }
        try  {
            outputStream = openFileOutput ( filename ,  Context.MODE_PRIVATE );
            outputStream.write ( string.getBytes ());
            outputStream.close ();

        }  catch  ( Exception e )  {
            e . printStackTrace ();
        }
        if(myFile.canRead()){
            Log.d(TAG, "onCreate: 1.can read file =" + filename);
        }*/
        Log.d(TAG, "onCreate: Path = " + Environment.getDataDirectory().getPath());
        Log.d(TAG, "onCreate: AbsPath = " + Environment.getDataDirectory().getAbsolutePath());
        try {
            Log.d(TAG, "onCreate: canPath = " + Environment.getDataDirectory().getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "onCreate: extStor Path = " + Environment.getExternalStorageDirectory().getPath());
        Log.d(TAG, "onCreate: extStor AbsPath = " + Environment.getExternalStorageDirectory().getAbsolutePath());
        try {
            Log.d(TAG, "onCreate: extStor canPath = " + Environment.getExternalStorageDirectory().getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onCreate: dlCache Path = " + Environment.getDownloadCacheDirectory().getPath());
        Log.d(TAG, "onCreate: dlCache AbsPath = " + Environment.getDownloadCacheDirectory().getAbsolutePath());
        try {
            Log.d(TAG, "onCreate: dlCache canPath = " + Environment.getDownloadCacheDirectory().getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "onCreate: DIR_MUSIC = " + Environment.getExternalStorageDirectory().getPath());
        Log.d(TAG, "onCreate: free space = " + Environment.getExternalStorageDirectory().getFreeSpace());
        Log.d(TAG, "onCreate: total space = " + Environment.getExternalStorageDirectory().getTotalSpace());
        Log.d(TAG, "onCreate: usable space = " + Environment.getExternalStorageDirectory().getUsableSpace());

        Log.d(TAG, "onCreate: canRead = " + isExternalStorageReadable());
        Log.d(TAG, "onCreate: canWrite = " + isExternalStorageWritable());

        File newFile = new File(Environment.getExternalStorageDirectory(), "new_file.txt");
        if (!newFile.isFile()) {
            try {
                //newFile.mkdir();
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fStream = new FileOutputStream(newFile, true);
            fStream.write("hello".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (newFile.isFile()) {
            try {
                FileInputStream fStream = new FileInputStream(newFile);
                fStream.read();
                Log.d(TAG, "onCreate: " + fStream);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean isExternalStorageWritable(){

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }
    public boolean isExternalStorageReadable(){

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }
}
