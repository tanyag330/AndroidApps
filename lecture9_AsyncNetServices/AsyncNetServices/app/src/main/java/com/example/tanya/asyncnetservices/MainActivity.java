package com.example.tanya.asyncnetservices;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startServBtn,stopServBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // final Intent serviceIntent = new Intent(this,MyIntentService.class);

        //serviceIntent.setAction(MyIntentService.ACTION_FOO);
        //serviceIntent.putExtra(MyIntentService.EXTRA_PARAM1,"param1");
        //serviceIntent.putExtra(MyIntentService.EXTRA_PARAM2,"param2");

        startServBtn = (Button) findViewById(R.id.btn_start_services);
        stopServBtn = (Button) findViewById(R.id.btn_stop_services);
        startServBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyIntentService.startActionFoo(MainActivity.this,"par1","par2");
                //startService(serviceIntent);
            }
        });
        stopServBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyIntentService.startActionBaz(MainActivity.this,"par1","par2");
                //stopService(serviceIntent);
            }
        });

    }
}
