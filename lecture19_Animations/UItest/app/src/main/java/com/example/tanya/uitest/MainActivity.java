package com.example.tanya.uitest;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    CustomView customView;
    TextView textView;
    RunnableCustomView customView1;

    RunnableCustomViewCircle customViewCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button changeColor = (Button) findViewById(R.id.color);
        customView = (CustomView) findViewById(R.id.custom_view);
        customView1 = (RunnableCustomView) findViewById(R.id.custom_view1);
        customViewCircle = (RunnableCustomViewCircle) findViewById(R.id.custom_view2);
        textView = (TextView) findViewById(R.id.text_view);
        changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation();
                //customView.setColor(Color.BLACK);

                // customView.setRadius(400);
            }
        });
/*



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        customView = (CustomView) findViewById(R.id.custom_view);
        textView = (TextView) findViewById(R.id.text);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation();
            }
        });
*/

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


    private void startAnimation() {
      /* Animator animator = ObjectAnimator.ofFloat(textView, "translationX" , 0f,500f);
        animator.setDuration(10000);
        animator.start();*/

        //for diagonally
       Path path =new Path();

        path.moveTo(0,0);
        path.lineTo(400, 0);
        path.lineTo(400, 400);
        path.lineTo(0, 400);
        path.lineTo(0, 0);

        ValueAnimator animator1 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            animator1 = ObjectAnimator.ofFloat(textView, "x" ,"y"  , path);
        }
        animator1.setDuration(1000);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.start();


       /* Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(0.5f, 200f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 400f);
        PropertyValuesHolder phv = PropertyValuesHolder.ofKeyframe("radius", kf0, kf1, kf2);
        ValueAnimator animator2 = ObjectAnimator.ofPropertyValuesHolder(customView, phv);
        animator2.setDuration(1000);
        animator2.setInterpolator(new BounceInterpolator());
*/
      /*  animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

            }
        });*/
        //animator2.start();
    }


  /*  public void setRadius(float value) {
        customView.setRadius(value);
    }
    */
    public void setRadius(float value) {
        customView.setRadius(value);
    }
}
