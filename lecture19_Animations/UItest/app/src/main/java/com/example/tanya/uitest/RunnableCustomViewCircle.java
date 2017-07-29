package com.example.tanya.uitest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Admin on 7/24/2016.
 */
public class RunnableCustomViewCircle extends View {
    Paint paint;
    float radius = 0;

    private Runnable animateCicle = new Runnable() {
        @Override
        public void run() {
            boolean reachedEnd = false;
            radius = radius +10;
            if (radius <=300){
                invalidate();
            }
            else reachedEnd= true;
            if (!reachedEnd) {
                postDelayed(this, 15);//after what time to start
            }
        }
    };
    public RunnableCustomViewCircle(Context context) {
        super(context);
        init();
    }

    public RunnableCustomViewCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
       // paint.setStrokeWidth(10);
        post(animateCicle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, paint);
    }
}

