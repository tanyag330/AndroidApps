package com.example.tanya.intents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    public static final int REQUEST_SECOND_ACTIVITY = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToSecondActivity(View v){

        EditText etInput = (EditText) findViewById(R.id.et_input);
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("myName",etInput.getText().toString());
        startActivity(i);


       /* Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("myName",etInput.getText().toString());
        startActivityForResult(i,REQUEST_SECOND_ACTIVITY);
*/
        /* Intent i= new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://google.com"));
        startActivity(i);*/

        /*Intent intent = new Intent("android.intent.category.LAUNCHER");
        Intent.setClassName("com.facebook.katana","com.facebook.katana.LoginActivity0");
        startActivity(intent);*/
    }
/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);

        if ((requestCode)== REQUEST_SECOND_ACTIVITY && (resultCode == RESULT_OK)){

            TextView tvMainAct = (TextView) findViewById(R.id.tv_main_act);
            tvMainAct.setText(data.getStringExtra(SecondActivity.KEY_RETURN_STRING));

        }
    }*/

}
