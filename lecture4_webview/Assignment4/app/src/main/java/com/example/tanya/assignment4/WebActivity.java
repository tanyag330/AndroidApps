package com.example.tanya.assignment4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView wv = (WebView) findViewById(R.id.web_view);
        wv.loadUrl("https://developer.android.com/guide/index.html");

    }
}
