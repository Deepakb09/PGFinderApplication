package com.deepak.pgfinderapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public void insertPgDetails(int rent, String area, String name, String contact, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put("pgrent", rent);
        contentValues.put("pgarea", area);
        contentValues.put("pgname", name);
        contentValues.put("pgcontact", contact);
        contentValues.put("pgdesc", desc);
        sqLiteDatabase.insert("pgdetails", null, contentValues);
    }

    public ArrayList<String> readPGdetails(){
        String tableName = "pgdetails";
        String selectQuery = "SELECT * FROM "+tableName;
        sqLiteDatabase1 = myHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase1.rawQuery(selectQuery, null);

        ArrayList<String> arrayList = new ArrayList<String>();
        int j = 0;

        if(cursor.moveToFirst()){
            do{
                int rent = cursor.getInt(1);
                String area = cursor.getString(2);
                String pgname = cursor.getString(3);
                String contact = cursor.getString(4);
                String desc = cursor.getString(5);

                String data1 = ""+rent+"/"+area+"/"+pgname+"/"+contact+"/"+desc;
                arrayList.add(data1);

            }while (cursor.moveToNext());
        }

        cursor.close();
        return arrayList;
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
            db.execSQL("create table pgdetails(_id integer primary key, pgrent integer, pgarea text, " +
                        "pgname text, pgcontact text, pgdesc text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
