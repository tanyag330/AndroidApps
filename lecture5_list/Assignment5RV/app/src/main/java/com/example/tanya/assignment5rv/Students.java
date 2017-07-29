package com.example.tanya.assignment5rv;

import java.util.ArrayList;

/**
 * Created by Admin on 6/23/2016.
 */
public class Students {

    public static class Student {
        String name;
        int age;
        int photo;

        public Student(String name, int age, int photo) {
            this.name = name;
            this.age = age;
            this.photo = photo;
        }
    }

    public static ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>(100);

        for(int i=1;i<11;i++) {
            students.add(new Student("MEENU" + i, 5, R.drawable.meenu));
            students.add(new Student("SHIVANI" + i, 14, R.drawable.shivani));
            students.add(new Student("SHREYA" + i, 10, R.drawable.shreya));
            students.add(new Student("AKSHAY" + i, 9, R.drawable.akshay));
            students.add(new Student("PARUL" + i, 15, R.drawable.parul));
            students.add(new Student("PRIYA" + i, 12, R.drawable.priya));
            students.add(new Student("VANSHIKA" + i, 7, R.drawable.vanshika));
            students.add(new Student("KARAN" + i, 8, R.drawable.karan));
            students.add(new Student("AARTI" + i, 18, R.drawable.aarti));
            students.add(new Student("RAVEENA" + i, 16, R.drawable.raveena));
        }
        return students;
    }
}
