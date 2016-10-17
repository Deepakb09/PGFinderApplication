package com.deepak.pgfinderapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Deepak on 14-Oct-16.
 */
public class PGDatabase {
    MyHelper myHelper;
    SQLiteDatabase sqLiteDatabase, sqLiteDatabase1;

    public PGDatabase(Context context){
        myHelper = new MyHelper(context, "pgdetails.db", null, 1);
    }

    public void open(){
        sqLiteDatabase = myHelper.getWritableDatabase();
    }

    public void insertPgDetails(String advertisername, String pgname, float contact, String pgcity, String pgarea, int pgrent,
                                String negotiable, String gender, String typeoffood, String moredetails, String dateofposting,
                                int image1, int image2, String latitude, String longitude){
        ContentValues contentValues = new ContentValues();
        contentValues.put("advertisername", advertisername);
        contentValues.put("pgname", pgname);
        contentValues.put("contact", contact);
        contentValues.put("pgcity", pgcity);
        contentValues.put("pgarea", pgarea);
        contentValues.put("pgrent", pgrent);
        contentValues.put("negotiable", negotiable);
        contentValues.put("gender", gender);
        contentValues.put("typeoffood", typeoffood);
        contentValues.put("moredetails", moredetails);
        contentValues.put("dateofposting", dateofposting);
        contentValues.put("image1", image1);
        contentValues.put("image2", image2);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longitude);
        try {
            sqLiteDatabase.insert("pgdetails", null, contentValues);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Cursor queryPGDetails(){
        Cursor cursor = sqLiteDatabase.query("pgdetails", null, null, null, null, null, null, null);

        return cursor;
    }

    public void close(){
        sqLiteDatabase.close();
    }

    public class MyHelper extends SQLiteOpenHelper{
        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table pgdetails(_id integer primary key, advertisername text, pgname text, contact float, pgcity text, pgarea text, pgrent integer, negotiable text, gender text, typeoffood text, moredetails text, dateofposting date, image1 blob, image2 blob, latitude text, longitude text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
