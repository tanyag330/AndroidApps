package com.example.tanya.viewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager vp = (ViewPager) findViewById(R.id.viewPager);
        vp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));


    }
}
