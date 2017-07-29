package com.example.Tanya.loginform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databasehelper extends SQLiteOpenHelper{
  private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="contacts.db";
    private static final String TABLE_NAME ="contacts";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME ="name";
    private static final String COLUMN_PASSWORD ="password";
    private static final String COLUMN_AGE="age";
    private static final String COLUMN_BLOODGROUP ="bloodgroup";
    private static final String COLUMN_PHONENO ="phoneno";
    private static final String COLUMN_ADDRESS ="address";
    private static final String COLUMN_EMAIL ="email";

  SQLiteDatabase sqLiteDatabase;
//    sqLiteDatabase openOrCreateDatabase( DATABASE_NAME ,SQLiteDatabase.CREATE_IF_NECESSARY ,null);
    private static final String TABLE_CREATE="CREATE TABLE IF NOT EXISTS contacts(id integer, name text, password text primary key, age text, bloodgroup text not null, phoneno text, address text, email text);";

    public databasehelper(Context context)
    {

        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(TABLE_CREATE);

        this.sqLiteDatabase=sqLiteDatabase;
    }
     public void insertContact( contact c)
     {
         sqLiteDatabase=this.getWritableDatabase();
         ContentValues values=new ContentValues();

         String query="select * from contacts";
         Cursor cursor=sqLiteDatabase.rawQuery(query,null);

         int count=cursor.getCount();
         values.put(COLUMN_ID,count);
         values.put(COLUMN_NAME,c.getName());
         values.put(COLUMN_PASSWORD,c.getPassword());
         values.put(COLUMN_AGE,c.getAge());
         values.put(COLUMN_BLOODGROUP,c.getBloodgroup());
         values.put(COLUMN_PHONENO,c.getPhnno());
         values.put(COLUMN_ADDRESS,c.getAddress());
         values.put(COLUMN_EMAIL,c.getEmail());
         sqLiteDatabase.insert(TABLE_NAME,null,values);
         sqLiteDatabase.close();
     }
     public String SearchPass(String uname)
     {
         sqLiteDatabase=this.getReadableDatabase();
         String querry="select name,password from contacts";
         Cursor cursor=sqLiteDatabase.rawQuery(querry,null);
         String a,b;
         b="not found";
         if(cursor.moveToFirst())
         {
             do{
                 a=cursor.getString(0);
                 if(a.equals(uname))
                 {
                     b=cursor.getString(1);
                     break;
                 }
             }
             while(cursor.moveToNext());
         }
         return b;
     }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
      String querry="DROP TABLE IF EXISTS"+TABLE_NAME;
        sqLiteDatabase.execSQL(querry);
        this.onCreate(sqLiteDatabase);
    }
}
