package com.example.tanya.ass6students;

import java.util.ArrayList;

/**
 * Created by Admin on 6/25/2016.
 */
public class Students {

    public static class Student {

        String name;
        int studentId;
        String phone;
        int photo;

        public Student(String name, int studentId, String phone, int photo) {

            this.name = name;
            this.studentId = studentId;
            this.phone = phone;
            this.photo = photo;
        }
    }

    public static ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>(10);

        students.add(new Student("MEENU", 1, "9854825846", R.drawable.meenu));
        students.add(new Student("SHIVANI", 2, " 9955594914", R.drawable.shivani));
        students.add(new Student("SHREYA", 3, "9999582510", R.drawable.shreya));
        students.add(new Student("AKSHAY", 4, "9994778562", R.drawable.akshay));
        students.add(new Student("PARUL", 5, "9998756715", R.drawable.parul));
        students.add(new Student("PRIYA", 6, "9997845612", R.drawable.priya));
        students.add(new Student("VANSHIKA", 7, " 9999925867", R.drawable.vanshika));
        students.add(new Student("KARAN", 8, "9999845278", R.drawable.karan));
        students.add(new Student("AARTI", 9, "9875684718", R.drawable.aarti));
        students.add(new Student("RAVEENA", 10, "9785114759", R.drawable.raveena));

        return students;

    }
}
