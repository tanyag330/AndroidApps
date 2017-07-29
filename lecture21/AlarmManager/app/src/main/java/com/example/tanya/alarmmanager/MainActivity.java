package com.example.tanya.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(this, MyService.class);
        //startService(i);

        Intent activityIntent = new Intent(this, MyService.class);
        //  activityIntent.addFlags();//to add diff. flags
        PendingIntent pendingIntent = PendingIntent.getService(this, 111, i, 0);

        //     pendingIntent.send();


        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(alarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                //System.currentTimeMillis(),
                1 * 60 * 1000,
                pendingIntent);
        // mostly used
        // alarmManager.setRepeating();
        //alarmManager.setExact();
        //best precision

        alarmManager.cancel(pendingIntent);
    }
}
