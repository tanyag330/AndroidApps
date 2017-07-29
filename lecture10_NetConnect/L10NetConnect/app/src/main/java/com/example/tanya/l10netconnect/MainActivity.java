package com.example.tanya.l10netconnect;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnDownload;
    TextView tv1, tvEvName, tvEvLocation, tvEvType, tvEvTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDownload = (Button) findViewById(R.id.btn_download);
        //tv1 = (TextView) findViewById(R.id.tv1);
        tvEvName = (TextView) findViewById(R.id.tv_event_name);
        tvEvLocation = (TextView) findViewById(R.id.tv_event_location);
        tvEvType = (TextView) findViewById(R.id.tv_event_type);
        tvEvTopic = (TextView) findViewById(R.id.tv_event_topic);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkConnectionAndRequest();
            }
        });
    }

    public void checkConnectionAndRequest() {

        ConnectivityManager cMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cMgr.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {

            NetFetchTask myTask = new NetFetchTask(new NetFetchTask.PostExecuteListener() {
                @Override
                public void postExecuteDone(Event event) {

                    tvEvName.setText(event.getName());
                    tvEvLocation.setText(event.getLocation());
                    tvEvType.setText(event.getType());
                    tvEvTopic.setText(event.getTopic());
                    //Toast.makeText(MainActivity.this, "Response is:" + responseCode, Toast.LENGTH_SHORT).show();
                    //tv1.setText(result);
                }
            });
            myTask.execute("http://open-event.herokuapp.com/api/v2/events/4");
        } else {
            Toast.makeText(MainActivity.this, "will not work without internet", Toast.LENGTH_SHORT).show();
        }
    }
}