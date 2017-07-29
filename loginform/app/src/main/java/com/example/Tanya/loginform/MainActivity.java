package com.example.Tanya.loginform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     databasehelper helper =new databasehelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText username=(EditText)findViewById(R.id.editText7);
        final EditText userpass=(EditText)findViewById(R.id.editText8);
        final Button b1=(Button)findViewById(R.id.button2);
        final TextView register=(TextView)findViewById(R.id.textView4);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerintent=new Intent(MainActivity.this,RegisterActivity.class);
                MainActivity.this.startActivity(registerintent);
               Toast.makeText(getApplicationContext(),"TRY TO REGISTER",Toast.LENGTH_SHORT).show();

            }
        });

     b1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v)
         {
             String name=username.getText().toString();
             String pass=userpass.getText().toString();

             String password=helper.SearchPass(name);
            if(password.equals(pass))
            {
                Intent i=new Intent(MainActivity.this,UserAreaActivity.class);
                i.putExtra("Username",name);
                startActivity(i);
            }
             else
            {
                Toast.makeText(getApplicationContext(),"USERNAME AND PASSWORD DONT MATCH",Toast.LENGTH_SHORT).show();
            }
         }
     });
      }

}
