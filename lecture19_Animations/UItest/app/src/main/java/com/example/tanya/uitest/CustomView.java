package com.example.tanya.uitest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Admin on 7/24/2016.
 */

public class CustomView extends View{

    Paint paint;
    int color;
    float radius;


    public CustomView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.parseColor("#009688"));
       // paint.setColor(color);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        this.color = ta.getColor(R.styleable.CustomView_CircleColor, Color.BLUE);
        this.radius = ta.getDimension(R.styleable.CustomView_CircleRadius, 200);

        ta.recycle();
    init();}

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2,getHeight()/2,radius, paint);
        /*Path path =new Path();
        path.moveTo();*/

    }
    public void setColor(int color){
        paint.setColor(color);
        invalidate();
    }
    public void setRadius(float radius){
       this.radius = radius;
        invalidate();
    }

}
