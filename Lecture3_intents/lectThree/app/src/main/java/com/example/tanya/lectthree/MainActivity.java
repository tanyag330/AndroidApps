package com.example.tanya.lectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Profile";

    private EditText etVarOne,etVarTwo;
    private Button btnAdd, btnSub, btnMul, btnDiv;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//@layout/activity_main in xml
        etVarOne = (EditText)findViewById(R.id.et_var_one);
        etVarTwo = (EditText)findViewById(R.id.et_var_two);

        btnAdd = (Button) findViewById(R.id.button_add);
        btnSub = (Button) findViewById(R.id.button_sub);
        btnMul = (Button) findViewById(R.id.button_mul);
        btnDiv = (Button) findViewById(R.id.button_div);

        tvResult = (TextView) findViewById(R.id.tv_result);
        View.OnClickListener clickListener = new View.OnClickListener()
        {@Override
                public void onClick(View v){
            try{
                int a = etVarOne.getText().toString().isEmpty()?0:
                        Integer.parseInt(etVarOne.getText().toString());
                int b = etVarTwo.getText().toString().isEmpty()?0:
                        Integer.parseInt(etVarTwo.getText().toString());
                int c=0;
                switch (v.getId()){
                    case R.id.button_add:
                        c=a+b;
                        break;
                    case R.id.button_sub:
                        c=a-b;
                        break;
                    case R.id.button_mul:
                        c=a*b;
                        break;
                    case R.id.button_div:
                        c=a/b;
                        break;

                }
                tvResult.setText(String.valueOf(c));
            }
            catch (NumberFormatException e){
                Log.e(TAG, "onClick: number are not int",e );
                Toast.makeText(MainActivity.this,"number must be int",Toast.LENGTH_SHORT).show();
            }
            catch (ArithmeticException e){
                Log.e(TAG, "onClick: can`t be calculated ",e );
                Toast.makeText(MainActivity.this,"cannot calculate",Toast.LENGTH_SHORT).show();
            }

        }
        };

        btnAdd.setOnClickListener(clickListener);
        btnSub.setOnClickListener(clickListener);
        btnMul.setOnClickListener(clickListener);
        btnDiv.setOnClickListener(clickListener);

        Log.d(TAG, "onCreate: has been called");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart: ");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause: ");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop: ");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
