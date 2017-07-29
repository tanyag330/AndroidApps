package com.example.admin.todoapp.models;


import java.util.ArrayList;

public  class Utils {


    public static ArrayList<TaskModel> getTasks() {


        ArrayList<TaskModel> tasks = new ArrayList<>(5);
        tasks.add(new TaskModel("shopping","15-7-2016"));
        tasks.add(new TaskModel("gymming","16-7-2016"));
        tasks.add(new TaskModel("movie watch","17-7-2016"));
        tasks.add(new TaskModel("cricket","15-7-2016"));
        tasks.add(new TaskModel("football","15-7-2016"));


        return tasks;


    }





}
