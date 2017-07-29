package com.example.tanya.intents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String KEY_RETURN_STRING ="returnString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Intent recievedIntent = getIntent();
        String myName = recievedIntent.getStringExtra("myName");

        TextView tvSecondActivity = (TextView)findViewById(R.id.tv_second_activity);
        tvSecondActivity.setText("hello " + myName);
        // reusable
       //  ------------------------------or---------------------------
       // ((TextView)findViewById(R.id.tv_second_activity)).setText("hello " + getIntent().getStringExtra("myName"));//fluent api  , saves memory


    }
    /*protected void onStop(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_RETURN_STRING,((EditText) findViewById(R.id.et_return_string)).getText().toString());
                setResult(RESULT_OK, resultIntent);
        super.onStop();

    }*/

   /* @Override
    public void onBackPressed() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_RETURN_STRING,((EditText) findViewById(R.id.et_return_string)).getText().toString());
        setResult(RESULT_OK, resultIntent);
        super.onBackPressed();

    }*/
    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume: ");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

}
