package com.example.tanya.myfragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DynamicFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment);

        FirstFragment fragOne = new FirstFragment();
        SecondFragment fragTwo = new SecondFragment();

        FragmentManager fragMgr = getSupportFragmentManager();
        FragmentTransaction fragTxn = fragMgr.beginTransaction();

    }
}
