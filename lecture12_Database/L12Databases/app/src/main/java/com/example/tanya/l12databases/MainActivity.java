package com.example.tanya.l12databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tanya.l12databases.db.StudentTable;
import com.example.tanya.l12databases.models.Student;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MAIN ACTIVITY" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      SQLiteDatabase myDb = (new MyDatabaseOpener(this)).getWritableDatabase();
        //  myDb.execSQL("INSERT INTO student");

        SQLiteDatabase myDb = MyDatabaseOpener.openWritableDatabases(this);

        Student stu = new Student(2,"Tanya", 5 , 11);
        ContentValues values = new ContentValues();
        values.put(StudentTable.Columns.ID, stu.getId());
        values.put(StudentTable.Columns.NAME , stu.getName());
        values.put(StudentTable.Columns.AGE, stu.getAge());
        values.put(StudentTable.Columns.CLASS, stu.getStudentClass());

        long id = myDb.insert(StudentTable.TABLE_NAME, null, values);
        Log.e(TAG, "onCreate: id = " + id);

        String[] projection={
                StudentTable.Columns.ID,
                StudentTable.Columns.NAME
        };

        Cursor c = myDb.query(
                StudentTable.TABLE_NAME, projection, null, null , null, null, null
        );

        Log.d(TAG, "onCreate: cursor count" + c.getCount());
        while (c.moveToNext()) {

            int stuId = c.getInt(c.getColumnIndex(StudentTable.Columns.ID));
            String stuName = c.getString(c.getColumnIndex(StudentTable.Columns.NAME));
            Log.d(TAG, "onCreate: student = " + stuId + " " + stuName);
        }
    c.close();
    }
}
