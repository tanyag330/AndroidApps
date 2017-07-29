package com.example.tanya.assignment5lv;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Lists";

    ArrayList<Students.Student> studentList;
    ListView studentListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentList = Students.getStudents();
        studentListView = (ListView)findViewById(R.id.list_student);

        StudentListAdapter studentAdapter= new StudentListAdapter(studentList);
        studentListView.setAdapter(studentAdapter);

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
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return (position%2);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater li = getLayoutInflater();
            long startTime;
            startTime = SystemClock.uptimeMillis();
            if (position%2==0){
                convertView = li.inflate(R.layout.list_item_student_left, null);
            }
            else {
                convertView = li.inflate(R.layout.list_item_student_right, null);
            }

            TextView studentName = (TextView) convertView.findViewById(R.id.tv_student_name);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.iv_photo);
            TextView studentAge = (TextView) convertView.findViewById(R.id.tv_age);
            Students.Student thisStudent = getItem(position);

            studentName.setText(thisStudent.name);
            imageView.setImageResource(thisStudent.photo);
            studentAge.setText(String.valueOf(thisStudent.age));

            long timeElapsed = (SystemClock.uptimeMillis() - startTime);
            Log.d(TAG, "getView: call finished, took time = " + timeElapsed);

        return convertView;
        }
    }
}

