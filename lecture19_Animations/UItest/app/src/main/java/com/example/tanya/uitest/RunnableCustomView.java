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
public class RunnableCustomView extends View {
    Paint paint;
    float xPos = 0;

    private Runnable animateLine = new Runnable() {
        @Override
        public void run() {
            boolean reachedEnd = false;
            xPos = xPos+10;
            if (xPos <=getWidth()){
            invalidate();
            }
            else reachedEnd= true;
            if (!reachedEnd) {
                postDelayed(this, 15);//after what time to start
            }
        }
    };
    public RunnableCustomView(Context context) {
        super(context);
        init();
    }

    public RunnableCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        post(animateLine);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(0,getHeight()/2,xPos,getHeight()/2,paint);
    }
}
