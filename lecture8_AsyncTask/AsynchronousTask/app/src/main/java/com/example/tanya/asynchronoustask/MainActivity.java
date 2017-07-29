package com.example.tanya.asynchronoustask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = (Button) findViewById(R.id.my_button);

        final MyTask task = new MyTask(this);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  MyTask task = new MyTask(MainActivity.this);
                task.execute("a");
*/
                if (task.getStatus()!= AsyncTask.Status.RUNNING &&
                        task.getStatus()!= AsyncTask.Status.FINISHED){
                    task.execute();
                }else {
                    Toast.makeText(MainActivity.this, "Task already running", Toast.LENGTH_SHORT).show();
                }
              /*  try {
                    task.execute("a").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }*/
                //   task.doInBackground("a","b","c");
                // any no. of parameters can be passed.
            }
        });
    }
}
