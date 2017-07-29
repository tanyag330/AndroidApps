package com.example.admin.todoapp.models;


public class TaskModel {

    String Name;
    String deadline;
    int Done;
    long id;

    public TaskModel(String name, String deadline) {
        Name = name;
        this.deadline = deadline;
        Done = 0;
    }

    public String getName() {
        return Name;
    }

    public String getDeadline() {
        return deadline;
    }

    public long getId() {
        return id;
    }

    public int getDone() {
        return Done;
    }

    public int isDone() {
        return Done;

    }

    public void setDone(int done) {
        Done = done;
    }

    public void setId(long id) {
        this.id = id;
    }
}
