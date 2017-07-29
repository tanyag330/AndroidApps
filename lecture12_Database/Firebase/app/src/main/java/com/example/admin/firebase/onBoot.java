package com.example.admin.firebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class onBoot extends BroadcastReceiver {
    public onBoot() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

     //  context.startService(new Intent(context,longrunningservice.class));

    }
}
