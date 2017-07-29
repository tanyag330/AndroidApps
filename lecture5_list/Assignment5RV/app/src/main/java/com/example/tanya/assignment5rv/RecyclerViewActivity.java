package com.example.tanya.assignment5rv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    public static final String TAG = "RecyclerView";

    RecyclerView studentRecyclerView;
    ArrayList<Students.Student> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        studentRecyclerView = (RecyclerView) findViewById(R.id.student_list_recyclerview);
        studentList = Students.getStudents();

        studentRecyclerViewAdapter studentRecyclerViewAdapter = new studentRecyclerViewAdapter(studentList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        studentRecyclerView.setLayoutManager(layoutManager);

        studentRecyclerView.setAdapter(studentRecyclerViewAdapter);

        studentRecyclerViewAdapter.notifyDataSetChanged();

    }

    public class StudentRecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView studentName;
        public TextView studentStrength;
        public ImageView studentProfile;

        public StudentRecyclerViewHolder(View itemView) {
            super(itemView);

            studentName = (TextView) itemView.findViewById(R.id.tv_student_name);
            studentStrength = (TextView) itemView.findViewById(R.id.tv_age);
            studentProfile = (ImageView) itemView.findViewById(R.id.iv_photo);
        }
    }

    public class studentRecyclerViewAdapter extends RecyclerView.Adapter<StudentRecyclerViewHolder> {

        private ArrayList<Students.Student> mStudents;

        public studentRecyclerViewAdapter(ArrayList<Students.Student> mStudents) {
            this.mStudents = mStudents;
        }

        @Override
        public StudentRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d(TAG, "onCreateViewHolder: called");

            LayoutInflater li = getLayoutInflater();
            View itemView = li.inflate(R.layout.list_item_student_left, null);

            StudentRecyclerViewHolder studentRecyclerViewHolder = new StudentRecyclerViewHolder(itemView);
            return studentRecyclerViewHolder;
        }

        @Override
        public void onBindViewHolder(StudentRecyclerViewHolder holder, int position) {

            Students.Student thisStudent = mStudents.get(position);

            holder.studentName.setText(thisStudent.name);
            holder.studentProfile.setImageResource(thisStudent.photo);
            holder.studentStrength.setText(String.valueOf(thisStudent.age));

        }

        @Override
        public int getItemCount() {
            return mStudents.size();
        }
    }
}
