package com.example.tanya.pushmsgapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by Admin on 7/28/2016.
 */
public class MsgService extends FirebaseMessagingService {

    private static final String TAG ="MsgService" ;

    public MsgService() {
        super();
        Log.d(TAG, "MsgService: ");
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        //Log.d(TAG, "onMessageReceived: ");

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "onMessageReceived token : "+token);

        Map<String,String> msgData = remoteMessage.getData();
        for (String s:remoteMessage.getData().keySet()){
            Log.d(TAG, "onMessageReceived: "+  s + " : "+ msgData.get(s));
        }
    }
}
