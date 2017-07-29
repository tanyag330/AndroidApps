package com.example.tanya.assignment4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goToDocumentActivity(View v) {
        Intent i = new Intent(this, DocumentActivity.class);
        startActivity(i);
    }

    public void goToWebActivity(View v) {
        Intent i = new Intent(this, WebActivity.class);
        startActivity(i);
    }
}