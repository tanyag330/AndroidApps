package com.example.admin.firebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

public class MainActivity extends AppCompatActivity {


    Firebase mRef;
    FirebaseListAdapter<String> fireadapter;
    ListView msgs;
    EditText etmsg;


    public void send_msg(View v)
    {
        mRef.push().setValue(etmsg.getText().toString());


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etmsg=(EditText)findViewById(R.id.et_msg);
        Firebase.setAndroidContext(this);
        mRef=new Firebase("https://fir-119fd.firebaseio.com/message");

        fireadapter=new FirebaseListAdapter<String>(this,String.class,android.R.layout.simple_list_item_1,mRef) {
            @Override
            protected void populateView(View view, String s, int i) {

                TextView text= (TextView) view.findViewById(android.R.id.text1);
                text.setText(s);


            }
        };

        msgs= (ListView) findViewById(R.id.lv_msg);
        msgs.setAdapter(fireadapter);






    }
}
