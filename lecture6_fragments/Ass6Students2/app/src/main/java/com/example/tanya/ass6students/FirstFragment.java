package com.example.tanya.ass6students;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class FirstFragment extends Fragment {
    View.OnClickListener clickListener;


    public static final String ARG_STU_ID = "stuId";

    private int studentId;

    public FirstFragment() {
    }
    public static FirstFragment newInstance(int stuId) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_STU_ID,stuId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            studentId = getArguments().getInt(ARG_STU_ID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_first, container, false);
        rootView.setOnClickListener(clickListener);

        TextView studentName = (TextView) rootView.findViewById(R.id.tv_student_name);
        ImageView imageView1 = (ImageView)rootView.findViewById(R.id.iv_photo1);
        TextView studentAge = (TextView) rootView.findViewById(R.id.tv_phone_no_);

        studentName.setText(Students.getStudents().get(studentId).name);
        imageView1.setImageResource(Students.getStudents().get(studentId).photo);
        studentAge.setText(String.valueOf(Students.getStudents().get(studentId).phone));

        return rootView;
    }
}
