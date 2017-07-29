package com.example.tanya.mediaplayback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainAct" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            InputStream is = getResources().getAssets().open("data.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder sb = new StringBuilder();
            do{
                line = br.readLine();
                sb.append(line);
            }
            while  (line!= null);
            String jsonstring = sb.toString();
            JSONObject jobj = new JSONObject(jsonstring);
            Log.d(TAG, "onCreate: "+ jobj.getString("SomeKey"));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


/*

//          to Stop and empty buffer
        mediaPlayer.release();
        mediaPlayer = null;
*/
    }
}
