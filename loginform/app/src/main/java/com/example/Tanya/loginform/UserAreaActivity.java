package com.example.Tanya.loginform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        final TextView tv=(TextView)findViewById(R.id.textView3);
        Intent i=getIntent();
        String name=i.getStringExtra("Username");
        tv.setText(name);
    }
}
