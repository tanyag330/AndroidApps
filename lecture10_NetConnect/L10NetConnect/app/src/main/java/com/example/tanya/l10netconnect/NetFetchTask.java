package com.example.tanya.l10netconnect;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by Admin on 7/5/2016.
 */
public class NetFetchTask extends AsyncTask<String, Void, Event> {

    private static final String TAG = NetFetchTask.class.getSimpleName();

    public interface PostExecuteListener {
        public void postExecuteDone(Event ev);
    }

    private PostExecuteListener myListener;

    public NetFetchTask(PostExecuteListener pel) {
        myListener = pel;
    }
    public NetFetchTask(TextView tv){

    }

    @Override

    protected Event doInBackground(String... params) {

        Event myEvent = null;
        //String returnString ="";
        //int responseCode = 0;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(10000);
            urlConn.setReadTimeout(20000);

            urlConn.connect();

            if(urlConn.getResponseCode() == 200 )
            {
                String res  =isToString(urlConn.getInputStream());
                Log.d(TAG, "doInBackground: " + res);
                myEvent = getEventFromResponse(res);

            }/*else {
                returnString = "Response is : " + urlConn.getResponseCode();
            }
*/
           // InputStream is = urlConn.getInputStream();


        } catch (IOException |JSONException e ) {
            e.printStackTrace();
        }

        return myEvent;
    }


    @Override
    protected void onPostExecute(Event ev) {
        super.onPostExecute(ev);
        myListener.postExecuteDone(ev);
    }

    public String isToString(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is,"utf-8"));
       // char[] buffer = new char[10];
        StringBuilder sb =new StringBuilder();
        String line =r.readLine();
        while (line!= null && !line.isEmpty())
        {
            sb.append(line);
            line = r.readLine();
        }
        return sb.toString();
    }

    public Event getEventFromResponse(String resp) throws JSONException {
        JSONObject jObj = new JSONObject(resp);
        Event event = new Event(
                jObj.getString("name"),
                jObj.getString("location_name"),
                jObj.getString("type"),
                jObj.getString("topic")
        );
        return event;
    }
}