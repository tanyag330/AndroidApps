package com.example.tanya.l11vollley;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    Button btnDownload;
    EditText etEvId;
    RequestQueue queue;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        etEvId = (EditText) findViewById(R.id.event_id);
        btnDownload = (Button) findViewById(R.id.btn_download);

        queue = Volley.newRequestQueue(this);
        url = "http://open-event.herokuapp.com/api/v2/events/";

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int evId;
                try {
                    evId = Integer.valueOf(etEvId.getText().toString());

                } catch (Exception e ) {
                    evId= 4;
                }
                queue.add(strReqBuilder(evId));
            }
        });
    }
    
    public StringRequest strReqBuilder(int eventId) {
        String eventUrl = url + eventId;
        StringRequest strReq = new StringRequest(Request.Method.GET, eventUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                tv1.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getCause() != null) {
                    error.getCause().printStackTrace();
                }
                if (error.networkResponse != null) {

                    Toast.makeText(MainActivity.this, "Error = " + error.networkResponse.statusCode, Toast.LENGTH_SHORT).show();
                }
            }

        });
        //queue.add(strReq);

        return strReq;
    }

    @Override
    protected void onPause() {

        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        //get Access
        SharedPreferences.Editor ed =sPref.edit();
        // copy that can be edited
        int evId;
        try {
            evId = Integer.valueOf(etEvId.getText().toString());

        } catch (Exception e ) {
            evId= 4;
        }
        ed.putInt("event_id", evId);// happening in memory, change data.
        ed.apply();//commit returns boolean, apply happens lazily and does not return anything

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        /*int evId = prefs.getInt("event_id", -1);
        if (evId == -1){

        }*/

        int evId = prefs.getInt("event_id", 4);
        etEvId.setText(String.valueOf(evId));

        tv1.setText("Refreshing Data");
        queue.add(strReqBuilder(evId));
    }
}