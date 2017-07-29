package com.example.tanya.pushmsgapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Admin on 7/28/2016.
 */
public class IdService extends FirebaseInstanceIdService {
    private static final String  TAG = "IdService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.d(TAG, "onTokenRefresh: ");
    }

    public IdService() {
        super();
        Log.d(TAG, "IdService: ");
    }
}
