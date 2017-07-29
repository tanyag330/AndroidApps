package com.example.tanya.l15sensors;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private static final String TAG = "MainActivity" ;
    SensorManager sensorManager;
    long prevTimeStamp = 0;
    RelativeLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = (RelativeLayout) findViewById(R.id.background);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
       // ArrayList<Sensor> sensors = new ArrayList<>(sensorManager.getSensorList(Sensor.TYPE_ALL));

     /*   for(Sensor sensor:sensors){

            Log.d(TAG, "sensor : name = " + sensor.getName());
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT_WATCH){

                Log.d(TAG, "sensor : type =" + sensor.getStringType());
            }
            if(sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
                sensorManager.registerListener(this, sensor, 1000000000);
            }
        }*/

         Sensor accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelSensor, SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //Log.d(TAG, "onSensorChanged: sensor = " + sensorEvent.sensor.getName());
        if (prevTimeStamp ==0) prevTimeStamp = sensorEvent.timestamp;

        if (sensorEvent.timestamp> (prevTimeStamp +1000000000)) {

            prevTimeStamp = sensorEvent.timestamp;
            // Log.d(TAG, "onSensorChanged: value = " + sensorEvent.values[0]);

            Log.d(TAG, "x: " + sensorEvent.values[0]);
            Log.d(TAG, "y: " + sensorEvent.values[1]);
            Log.d(TAG, "z: " + sensorEvent.values[2]);
        }
            if(background!= null) {
            //setColor(sensorEvent.values[0],sensorEvent.values[1], sensorEvent.values[2], background);
            letsDisco(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2], background);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged: sensor = " + sensor.getName()
        + "accuracy = "+ accuracy);

    }

    private void setColor(float accX, float accY , float accZ, View v) {

        if (accX>3) {
            v.setBackgroundColor(Color.GREEN);
        }
        if (accX<-3){
            v.setBackgroundColor(Color.RED);
        }else {
            v.setBackgroundColor(Color.YELLOW);
        }
    }

    private void letsDisco(float accX, float accY , float accZ, View v) {
        int red = gravity2color(accX);
        int green = gravity2color(accY);
        int blue = gravity2color(accZ);

        v.setBackgroundColor(Color.argb(255, red, green, blue ));
    }
        private int gravity2color(float gravity) {

        return (int) (((gravity+10)/20)*255 );
    }
}