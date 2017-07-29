package com.example.tanya.l12databases.db;

/**
 * Created by Admin on 7/12/2016.
 */
public class StudentTable extends DbTable
{

    public static final String TABLE_NAME = "students";

    /*public interface Columns extends BaseColumns{

    }*/

    public interface Columns{
        String ID = "id";
        String NAME = "name";
        String AGE = "age";
        String CLASS = "class";
    }
    public static final String TABLE_CREATE_CMO =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + LBR
            + Columns.ID + TYPE_INT_PK + COMMA
            + Columns.NAME + TYPE_TEXT + COMMA
            + Columns.AGE + TYPE_INT + COMMA
            + Columns.CLASS + TYPE_INT
            + RBR + ";" ;

}
