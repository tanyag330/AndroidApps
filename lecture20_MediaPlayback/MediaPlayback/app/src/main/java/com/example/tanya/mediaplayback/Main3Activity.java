package com.example.tanya.mediaplayback;


import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

public class Main3Activity extends AppCompatActivity {


    Camera camera;
    private static final String TAG = "camera";
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        surfaceView = (SurfaceView) findViewById(R.id.surface);
        surfaceHolder = surfaceView.getHolder();


        /*camera = Camera.open();
        Camera.Parameters parameters = camera.getParameters();

        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();

        for (Camera.Size size : sizes){
            Log.d(TAG, "onCreate: "+ size.width + " "+ size.height);
        }


        camera.release();*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera = Camera.open();
        Camera.Parameters parameters = camera.getParameters();

        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();

        for (Camera.Size size : sizes){
            Log.d(TAG, "onCreate: "+ size.width + " "+ size.height);
        }
        parameters.setPreviewSize(960, 540);
        camera.setDisplayOrientation(90);
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                    camera.setPreviewDisplay(surfaceHolder);
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });

        camera.startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            camera.release();
        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }
}
