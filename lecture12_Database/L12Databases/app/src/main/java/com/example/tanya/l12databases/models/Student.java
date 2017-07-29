package com.example.tanya.l12databases.models;

/**
 * Created by Admin on 7/12/2016.
 */
public class Student {

    int id;
    String name;
    int studentClass;
    int age;

    public Student(int id, String name, int studentClass, int age) {
        this.id = id;
        this.name = name;
        this.studentClass = studentClass;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStudentClass() {
        return studentClass;
    }
}
