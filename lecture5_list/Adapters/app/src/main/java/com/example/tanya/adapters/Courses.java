package com.example.tanya.adapters;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Admin on 6/21/2016.
 */
public class Courses {

    public static class Course{
        String name;
        int capacity;
        int enrollment;

        public Course(String name, int enrollment, int capacity) {
            this.name = name;
            this.enrollment = enrollment;
            this.capacity = capacity;
        }
    }

    public static ArrayList<Course> getCourse(){
        ArrayList<Course> courses = new ArrayList<>(100);
        for(int i=2014; i<2024; i++) {
            courses.add(new Course("C++" + i, 50, 40));
            courses.add(new Course("JAVA" + i, 50, 32));
            courses.add(new Course("PYTHON" + i, 50, 34));
            courses.add(new Course("PHP" + i, 50, 42));
            courses.add(new Course("PERL" + i, 50, 25));
            courses.add(new Course("GO" + i, 50, 27));
            courses.add(new Course("JAVASCRIPT" + i, 50, 22));
            courses.add(new Course("SHELL" + i, 50, 37));
            courses.add(new Course("LUA" + i, 50, 29));
            courses.add(new Course("BASIC" + i, 50, 45));
        }
        return courses;
    }
}
