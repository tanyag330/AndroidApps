package com.example.admin.todoapp.DB;




public class TaskTable extends  DbTable {


    public static final String tb_name="tasks";

    public static final String[] projection={Columns.ID,Columns.NAME,Columns.DEADLINE,Columns.DONE};

    public interface  Columns
    {
        String ID="id";
        String NAME="name";
        String DEADLINE="deadline";
        String DONE="done";


    }


    public static final String TABLE_CREATE_CMD = "CREATE TABLE IF NOT EXISTS " + tb_name
            + LBR + Columns.ID + TYPE_INT_PK_AUTO + COMMA
            + Columns.NAME + TYPE_TEXT + COMMA
            + Columns.DEADLINE + TYPE_TEXT + COMMA
            + Columns.DONE + TYPE_INT
            + RBR + ";";




}
