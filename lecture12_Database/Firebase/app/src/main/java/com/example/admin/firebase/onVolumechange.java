package com.example.admin.firebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class onVolumechange extends BroadcastReceiver {
    public onVolumechange() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

       // context.startService(new Intent(context,longrunningservice.class));
    }
}
