package com.example.tanya.l12databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tanya.l12databases.db.StudentTable;

/**
 * Created by Admin on 7/12/2016.
 */
public class MyDatabaseOpener extends SQLiteOpenHelper {

    public static final String DB_NAME = "mydatabase";
    public static final int DB_VER = 1;

    private static MyDatabaseOpener myDatabaseOpener = null;

    public static SQLiteDatabase openReadableDatabases(Context c ){
        if (myDatabaseOpener == null){
            myDatabaseOpener = new MyDatabaseOpener(c);
        }

        return myDatabaseOpener.getReadableDatabase();
    }

    public static SQLiteDatabase openWritableDatabases(Context c ){
        if (myDatabaseOpener == null){
            myDatabaseOpener = new MyDatabaseOpener(c);
        }

        return myDatabaseOpener.getWritableDatabase();
    }


    private MyDatabaseOpener(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

  //      StudentTable.Columns.ID
        db.execSQL(StudentTable.TABLE_CREATE_CMO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
