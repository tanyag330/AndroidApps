package com.example.tanya.assignment5rv;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
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
        studentListView = (ListView) findViewById(R.id.list_student);

        StudentListAdapter studentAdapter = new StudentListAdapter(studentList);
        studentListView.setAdapter(studentAdapter);


    }
    private class StudentListAdapter extends BaseAdapter {


        class StudentViewHolder {
            TextView studentNameView;
            TextView studentAgeView;
            ImageView studentPhotoView;
        }

        private ArrayList<Students.Student> mStudents;

        public StudentListAdapter(ArrayList<Students.Student> mStudents) {
            this.mStudents = mStudents;
        }

        @Override
        public int getCount() {
            return mStudents.size();
        }

        @Override
        public Students.Student getItem(int position) {
            return mStudents.get(position);
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
            StudentViewHolder studentViewHolder;

            Log.d(TAG, "getView: called");
            long startTime;

            if (convertView == null |  getItemViewType(position)!=(position%2)) {
                startTime = SystemClock.uptimeMillis();
                Log.d(TAG, "getView: convertView == null");

                if (position%2==0){
                    convertView = li.inflate(R.layout.list_item_student_left, parent,false);
                }
                else {
                    convertView = li.inflate(R.layout.list_item_student_right, parent,false);
                }


                studentViewHolder = new StudentViewHolder();
                studentViewHolder.studentNameView =
                        (TextView) convertView.findViewById(R.id.tv_student_name);
                studentViewHolder.studentAgeView =
                        (TextView) convertView.findViewById(R.id.tv_age);
                studentViewHolder.studentPhotoView =
                        (ImageView) convertView.findViewById(R.id.iv_photo);

                convertView.setTag(studentViewHolder);
            }
        else {
                startTime = SystemClock.uptimeMillis();
                Log.d(TAG, "getView: convertView != null");
                studentViewHolder = (StudentViewHolder) convertView.getTag();
            }

            Students.Student thisStudent = getItem(position);


            studentViewHolder.studentNameView.setText(thisStudent.name);
            studentViewHolder.studentAgeView.setText(String.valueOf(thisStudent.age));
            studentViewHolder.studentPhotoView.setImageResource(thisStudent.photo);


            long timeElapsed = (SystemClock.uptimeMillis() - startTime);
            Log.d(TAG, "getView: call finished, took time = " + timeElapsed);

            return convertView;
        }
    }

}
