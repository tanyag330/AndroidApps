package com.example.tanya.novels;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Admin on 7/6/2016.
 */
public class NetFetchTask extends AsyncTask<String, Void, String> {

    private static final String TAG = NetFetchTask.class.getSimpleName();

    public interface PostExecuteListener {
        public void postExecuteDone(String res);
    }

    private PostExecuteListener myListener;

    public NetFetchTask(PostExecuteListener pel) {
        myListener = pel;
    }
    public NetFetchTask(TextView tv){

    }

    @Override

    protected String doInBackground(String... params) {

        String returnString ="";

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

            }/*else {
                returnString = "Response is : " + urlConn.getResponseCode();
            }
*/
            // InputStream is = urlConn.getInputStream();
        } catch (IOException e ) {
            e.printStackTrace();
        }

        return returnString;
    }


    @Override
    protected void onPostExecute(String res) {
        super.onPostExecute(res);
        myListener.postExecuteDone(res);
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

}