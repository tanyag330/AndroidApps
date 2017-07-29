package com.example.tanya.asynchronoustask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Admin on 6/28/2016.
 */
public class MyTask extends AsyncTask<String, Void, Long>{


    private static final String TAG ="MyTask" ;

    private Context ctx;

    public MyTask(Context c){
        ctx = c;
    }

    @Override
    protected Long doInBackground(String... params) {

        long startTime = SystemClock.uptimeMillis();
        long timesRun =0;
        while((SystemClock.uptimeMillis()-startTime)<10000){
                timesRun++;
        }

        return timesRun;
    // return result type
        }

    @Override
    protected void onPostExecute(Long mlong) {
        super.onPostExecute(mlong);
        //Toast.makeText(MyTask.this, "mlong", Toast.LENGTH_LONG).show();
        Toast.makeText(ctx, "run : " +mlong, Toast.LENGTH_LONG).show();
        Log.d(TAG, "onPostExecute: ran "+ mlong + " times");
    }
}
