package com.example.tanya.adapters;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";


    ArrayList<Courses.Course> courseList;
    ListView courseListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        courseList = Courses.getCourse();
        courseListView = (ListView)findViewById(R.id.list_course);

        CourseListAdapter courseAdapter= new CourseListAdapter(courseList);
        courseListView.setAdapter(courseAdapter);

    }
    private class CourseListAdapter extends BaseAdapter{

        class CourseViewHolder{
            TextView courseNameView;
            TextView courseStrengthView;

        }

        private ArrayList<Courses.Course> mCourses;
        public CourseListAdapter(ArrayList< Courses.Course> mCourses)
        {
            this.mCourses = mCourses;
        }


        @Override
        public int getCount() {
            return mCourses.size();
        }

        @Override
        public Courses.Course getItem(int position) {
            return mCourses.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater li = getLayoutInflater();
            CourseViewHolder courseViewHolder;
            Log.d(TAG,"getView: called");
            // long startTime;
            if(convertView == null){
            //    startTime =
                Log.d(TAG,"getView: convertView == null");
                convertView = li.inflate(R.layout.list_item_course,null);
                courseViewHolder = new CourseViewHolder();
                courseViewHolder.courseNameView =
                        (TextView)convertView.findViewById(R.id.tv_course_name);
                courseViewHolder.courseStrengthView =
                        (TextView)convertView.findViewById(R.id.tv_course_strength_perc);
                convertView.setTag(courseViewHolder);
            }else{
                Log.d(TAG,"getView: convertView != null");
                courseViewHolder = (CourseViewHolder)convertView.getTag();
            }
            Courses.Course thisCourse = getItem(position);

            courseViewHolder.courseNameView.setText(thisCourse.name);
            float strengthPerc = ((float) thisCourse.enrollment / (float) thisCourse.capacity) * 100;

            courseViewHolder.courseStrengthView.setText(String.valueOf(strengthPerc));

            if(strengthPerc<33.3){
                courseViewHolder.courseStrengthView.setTextColor(Color.RED);
            }
            else if(strengthPerc<66.6) {
                courseViewHolder.courseStrengthView.setTextColor(Color.YELLOW);
            }
            else {
                courseViewHolder.courseStrengthView.setTextColor(Color.GREEN);
            }

            /*

            TextView courseName = (TextView) convertView.findViewById(R.id.tv_course_name);
            TextView courseStrengthPerc = (TextView) convertView.findViewById(R.id.tv_course_strength_perc);


            Courses.Course thisCourse = getItem(position);

           courseName.setText(thisCourse.name);

            float strengthPerc = ((float)thisCourse.enrollment/ (float)thisCourse.capacity)*100;
            courseStrengthPerc.setText(String.valueOf(strengthPerc));

            if(strengthPerc<33.3){
                courseStrengthPerc.setTextColor(Color.RED);
            }
            else if(strengthPerc<66.6) {
                courseStrengthPerc.setTextColor(Color.YELLOW);
            }
            else {
                courseStrengthPerc.setTextColor(Color.GREEN);
            }
*/
            return convertView;
        }
    }
}
