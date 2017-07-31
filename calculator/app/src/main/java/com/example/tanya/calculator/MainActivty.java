package com.example.tanya.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivty extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private EditText etVarOne;
    private Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven,
            btnEight, btnNine, btnZero, btnClr, btnAdd, btnSub, btnMul, btnDiv, btnEquals;
    private TextView tvResult;
    int op1;
    int op2;
    String optr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activty);

        etVarOne = (EditText) findViewById(R.id.et_var_one);

        btnOne = (Button) findViewById(R.id.btn_one);
        btnTwo = (Button) findViewById(R.id.btn_two);
        btnThree = (Button) findViewById(R.id.btn_three);
        btnFour = (Button) findViewById(R.id.btn_four);
        btnFive = (Button) findViewById(R.id.btn_five);
        btnSix = (Button) findViewById(R.id.btn_six);
        btnSeven = (Button) findViewById(R.id.btn_seven);
        btnEight = (Button) findViewById(R.id.btn_eight);
        btnNine = (Button) findViewById(R.id.btn_nine);
        btnZero = (Button) findViewById(R.id.btn_zero);
        btnClr = (Button) findViewById(R.id.btn_clr);
        btnEquals = (Button) findViewById(R.id.btn_equal);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnSub = (Button) findViewById(R.id.btn_sub);
        btnMul = (Button) findViewById(R.id.btn_mul);
        btnDiv = (Button) findViewById(R.id.btn_div);

        tvResult = (TextView) findViewById(R.id.tv_result);


        try{
            btnOne.setOnClickListener(clickListener);

            btnTwo.setOnClickListener(clickListener);

            btnThree.setOnClickListener(clickListener);

            btnFour.setOnClickListener(clickListener);

            btnFive.setOnClickListener(clickListener);

            btnSix.setOnClickListener(clickListener);

            btnSeven.setOnClickListener(clickListener);

            btnEight.setOnClickListener(clickListener);

            btnNine.setOnClickListener(clickListener);

            btnZero.setOnClickListener(clickListener);

            btnClr.setOnClickListener(clickListener);

            btnAdd.setOnClickListener(clickListener);

            btnSub.setOnClickListener(clickListener);

            btnMul.setOnClickListener(clickListener);

            btnDiv.setOnClickListener(clickListener);

            btnEquals.setOnClickListener(clickListener);
        }
        catch(Exception e){

        }
    }
    View.OnClickListener clickListener = new View.OnClickListener() {

        public void operation(){
        if(optr.equals("+")){
            op2 = Integer.parseInt(etVarOne.getText().toString());
            etVarOne.setText("");
            op1 = op1 + op2;
            etVarOne.setText("Result : " + Integer.toString(op1));
        }
        else if(optr.equals("-")){
            op2 = Integer.parseInt(etVarOne.getText().toString());
            etVarOne.setText("");
            op1 = op1 - op2;
            etVarOne.setText("Result : " + Integer.toString(op1));
        }
        else if(optr.equals("*")){
            op2 = Integer.parseInt(etVarOne.getText().toString());
            etVarOne.setText("");
            op1 = op1 * op2;
            etVarOne.setText("Result : " + Integer.toString(op1));
        }
        else if(optr.equals("/")){
            op2 = Integer.parseInt(etVarOne.getText().toString());
            etVarOne.setText("");
            op1 = op1 / op2;
            etVarOne.setText("Result : " + Integer.toString(op1));
        }
    }
    @Override
    public void onClick(View arg0) {
        String str =  etVarOne.getText().toString();
        switch(arg0.getId()) {
            case R.id.btn_one:
                if (op2 != 0) {
                    op2 = 0;
                    etVarOne.setText("");
                }
                str = str.concat("1");
                etVarOne.setText(str);
                break;
            case R.id.btn_two:
                if (op2 != 0) {
                    op2 = 0;
                    etVarOne.setText("");
                }
                str = str.concat("2");
                etVarOne.setText(str);
                break;
            case R.id.btn_three:
                if (op2 != 0) {
                    op2 = 0;
                    etVarOne.setText("");
                }
                str = str.concat("3");
                etVarOne.setText(str);
                break;
            case R.id.btn_four:
                if (op2 != 0) {
                    op2 = 0;
                    etVarOne.setText("");
                }
                str = str.concat("4");
                etVarOne.setText(str);
                break;
            case R.id.btn_five:
                if (op2 != 0) {
                    op2 = 0;
                    etVarOne.setText("");
                }
                str = str.concat("5");
                etVarOne.setText(str);
                break;
            case R.id.btn_six:
                if (op2 != 0) {
                    op2 = 0;
                    etVarOne.setText("");
                }
                str = str.concat("6");
                etVarOne.setText(str);
                break;
            case R.id.btn_seven:
                if (op2 != 0) {
                    op2 = 0;
                    etVarOne.setText("");
                }
                str = str.concat("7");
                etVarOne.setText(str);
                break;
            case R.id.btn_eight:
                if (op2 != 0) {
                    op2 = 0;
                    etVarOne.setText("");
                }
                str = str.concat("8");
                etVarOne.setText(str);

                break;
            case R.id.btn_nine:
                if (op2 != 0) {
                    op2 = 0;
                    etVarOne.setText("");
                }
                str = str.concat("9");
                etVarOne.setText(str);

                break;
            case R.id.btn_zero:
                if (op2 != 0) {
                    op2 = 0;
                    etVarOne.setText("");
                }
                str = str.concat("0");
                etVarOne.setText(str);

                break;
            case R.id.btn_clr:
                op1 = 0;
                op2 = 0;
                etVarOne.setText("");
                etVarOne.setHint("Perform Operation :)");

                break;
            case R.id.add:
                optr = "+";
                if (op1 == 0) {
                    op1 = Integer.parseInt(etVarOne.getText().toString());
                    etVarOne.setText("");
                } else if (op2 != 0) {
                    op2 = 0;
                    etVarOne.setText("");
                } else {
                    op2 = Integer.parseInt(etVarOne.getText().toString());
                    etVarOne.setText("");
                    op1 = op1 + op2;
                    etVarOne.setText("Result : " + Integer.toString(op1));
                }
                break;
            case R.id.btn_sub:
                optr = "-";
                if (op1 == 0) {
                    op1 = Integer.parseInt(etVarOne.getText().toString());
                    etVarOne.setText("");
                } else if (op2 != 0) {
                    op2 = 0;
                    etVarOne.setText("");
                } else {
                    op2 = Integer.parseInt(etVarOne.getText().toString());
                    etVarOne.setText("");
                    op1 = op1 - op2;
                    etVarOne.setText("Result : " + Integer.toString(op1));
                }
                break;
            case R.id.btn_mul:
                optr = "*";
                if (op1 == 0) {
                    op1 = Integer.parseInt(etVarOne.getText().toString());
                    etVarOne.setText("");
                } else if (op2 != 0) {
                    op2 = 0;
                    etVarOne.setText("");
                } else {
                    op2 = Integer.parseInt(etVarOne.getText().toString());
                    etVarOne.setText("");
                    op1 = op1 * op2;
                    etVarOne.setText("Result : " + Integer.toString(op1));
                }
                break;
            case R.id.btn_div:
                optr = "/";
                if (op1 == 0) {
                    op1 = Integer.parseInt(etVarOne.getText().toString());
                    etVarOne.setText("");
                } else if (op2 != 0) {
                    op2 = 0;
                    etVarOne.setText("");
                } else {
                    op2 = Integer.parseInt(etVarOne.getText().toString());
                    etVarOne.setText("");
                    op1 = op1 / op2;
                    etVarOne.setText("Result : " + Integer.toString(op1));
                }
                break;
            case R.id.btn_equal:
                if (!optr.equals(null)) {
                    if (op2 != 0) {
                        if (optr.equals("+")) {
                            etVarOne.setText("");
							/*op1 = op1 + op2;*/
                            etVarOne.setText("Result : " + Integer.toString(op1));
                        } else if (optr.equals("-")) {
                            etVarOne.setText("");/*
							op1 = op1 - op2;*/
                            etVarOne.setText("Result : " + Integer.toString(op1));
                        } else if (optr.equals("*")) {
                            etVarOne.setText("");/*
							op1 = op1 * op2;*/
                            etVarOne.setText("Result : " + Integer.toString(op1));
                        } else if (optr.equals("/")) {
                            etVarOne.setText("");/*
							op1 = op1 / op2;*/
                            etVarOne.setText("Result : " + Integer.toString(op1));
                        }
                    } else {
                        operation();
                    }
                }
                break;

        }
    }
};
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
