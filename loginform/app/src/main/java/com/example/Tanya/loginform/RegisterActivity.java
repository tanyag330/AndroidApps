package com.example.Tanya.loginform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity
{
   databasehelper helper=new databasehelper(this);
    String name,pass1,pass2,ages,blood,phnno,address,email;
    int age;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
      Intent i=getIntent();

        final EditText etname=(EditText)findViewById(R.id.editText);
        final EditText etpass1=(EditText)findViewById(R.id.editText2);
        final EditText etpass2=(EditText)findViewById(R.id.editText10);
        final EditText etAge=(EditText)findViewById(R.id.editText3);
        final EditText etblood=(EditText)findViewById(R.id.editText4);
        final EditText etphnno=(EditText)findViewById(R.id.editText6);
        final EditText etaddress=(EditText)findViewById(R.id.editText5);
        final EditText etemail=(EditText)findViewById(R.id.editText9);
        final Button submit=(Button)findViewById(R.id.button);



        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                name=etname.getText().toString();
                pass1=etpass1.getText().toString();
                pass2=etpass2.getText().toString();

                if (name.equals("") || pass1.equals("") ||pass2.equals(""))
                {

                    Toast.makeText(getApplicationContext(), "Field Vaccant",
                            Toast.LENGTH_LONG).show();
                }
                else if (!pass1.equals(pass2))
                {
                    Toast.makeText(getApplicationContext(), "PASSWORD DONT MATCH", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    ages = etAge.getText().toString();
                    age = Integer.parseInt(ages);
                    if (age < 18) {
                        Toast.makeText(getApplicationContext(), "AGE MUST BE GREATER THAN 18", Toast.LENGTH_SHORT).show();
                    } else {
                        blood = etblood.getText().toString();
                        phnno = etphnno.getText().toString();
                        address = etaddress.getText().toString();
                        email = etemail.getText().toString();
                    }
                }
                        contact c = new contact();
                        c.setName(name);
                        c.setPassword(pass1);
                        c.setAge(age);
                        c.setBloodgroup(blood);
                        c.setPhnno(phnno);
                        c.setAddress(address);
                        c.setEmail(email);
                        helper.insertContact(c);
                        Toast.makeText(getApplicationContext(), "REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT).show();

                      //  Intent back=new Intent(RegisterActivity.this,MainActivity.class);
                      //  startActivity(back);
            }

        });


    }

}
