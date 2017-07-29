package com.example.tanya.assignment4;

import android.content.Intent;
import android.support.v4.provider.DocumentFile;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.FileNotFoundException;

public class DocumentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);

      //  ViewSwitcher textView = (ViewSwitcher)findViewById(R.id.document_view);
      //  textView.findViewWithTag("ftp://1_JAVA_AND_OOP");

        TextSwitcher textView = (TextSwitcher)findViewById(R.id.textSwitcher);
      //  textView.
        /*try{
           openFileInput("ftp://1_JAVA_AND_OOP");
       }
       catch (FileNotFoundException e){
           Toast.makeText(DocumentActivity.this, "File not found", Toast.LENGTH_SHORT).show();
       }*/
    }
}
