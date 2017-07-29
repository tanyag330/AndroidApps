package com.example.tanya.ass6students;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Lists";

    ArrayList<Students.Student> studentList;
    ArrayList<Fragment> fragmentList = null;
    ListView studentListView;
    TextView tvStName, tvStPhone,tvStCollege;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentList = Students.getStudents();
        fragmentList = new ArrayList<>();

        for (int i=0 ; i<studentList.size() ; i++){
            fragmentList.add(FirstFragment.newInstance(i));
        }

        studentListView = (ListView)findViewById(R.id.list_student);

        final StudentListAdapter studentAdapter= new StudentListAdapter(studentList);
        studentListView.setAdapter(studentAdapter);

        tvStName = (TextView) findViewById(R.id.tv_student_name);
        tvStCollege = (TextView) findViewById(R.id.tv_college_name);
        tvStPhone = (TextView) findViewById(R.id.tv_phone_no_);
        ivImage = (ImageView) findViewById(R.id.iv_photo1);

        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container , fragmentList.get(position));
                transaction.commit();
            }
        });
    }

    private class StudentListAdapter extends BaseAdapter {

        private ArrayList<Students.Student> mStudent;
        public StudentListAdapter(ArrayList< Students.Student> mStudent)
        {
            this.mStudent = mStudent;
        }

        @Override
        public int getCount() {

            return mStudent.size();
        }

        @Override
        public Students.Student getItem(int position) {
            return mStudent.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater li = getLayoutInflater();
            long startTime;
            startTime = SystemClock.uptimeMillis();
            convertView =li.inflate(R.layout.list_item_student,null);

            ImageView imageView = (ImageView)convertView.findViewById(R.id.iv_photo);
            final Students.Student thisStudent = getItem(position);
            imageView.setImageResource(thisStudent.photo);

            long timeElapsed = (SystemClock.uptimeMillis() - startTime);
            Log.d(TAG, "getView: call finished, took time = " + timeElapsed);

            return convertView;
        }
    }



}