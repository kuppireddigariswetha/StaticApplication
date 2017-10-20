package com.example.roja_pc.todoproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Roja-PC on 22-03-2017.
 */

public class DBController extends SQLiteOpenHelper {
    private static final String LOGCAT = null;
    SQLiteDatabase database;
    public DBController(Context applicationcontext) {
        super(applicationcontext, "PrdouctDB.db", null, 1);  // creating DATABASE
        Log.d(LOGCAT, "Created");
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE IF NOT EXISTS proinfo ( Id INTEGER PRIMARY KEY, Studentname TEXT, Uniqueid TEXT, Fathername TEXT , School TEXT , District TEXT , Dob TEXT, Class TEXT , Section TEXT , Mobile TEXT)";
        database.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old,
                          int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS proinfo";
        database.execSQL(query);
        onCreate(database);
    }
    public Cursor onUpdate2(int i) {
        SQLiteDatabase database = this.getWritableDatabase();
        String query1;
        query1="select * from proinfo where Id='"+i+"'";
        SQLiteDatabase sql = this.getReadableDatabase();
        Cursor cur = sql.rawQuery(query1, null);
        return cur;
    }

    public Cursor onUpdate1(String uuid) {
        SQLiteDatabase database = this.getWritableDatabase();
        String query;
        query = "select * from proinfo where Uniqueid = '" + uuid + "'";
        SQLiteDatabase sql = this.getReadableDatabase();
        Cursor cur = sql.rawQuery(query, null);
        return cur;
    }
    public ArrayList<HashMap<String, String>> getAllProducts() {
        ArrayList<HashMap<String, String>> proList;
        proList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM proinfo";
        SQLiteDatabase database = this.getReadableDatabase();
        Log.v("try==59","inside");
        Cursor cursor = database.rawQuery(selectQuery, null);
        Log.v("try==59",cursor.toString());
        if (cursor.moveToFirst()) {
            Log.v("try==62","inside");
            do {
                Log.v("try==64","inside");
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Id", cursor.getString(0));
                map.put("StudentName", cursor.getString(1));
                map.put("Uniqueid", cursor.getString(2));
                map.put("Fathername", cursor.getString(3));
                map.put("School", cursor.getString(4));
                map.put("District", cursor.getString(5));
                map.put("Dob", cursor.getString(6));
                map.put("Class", cursor.getString(7));
                map.put("Section", cursor.getString(8));
                map.put("Mobile", cursor.getString(9));
                proList.add(map);

            } while (cursor.moveToNext());
        }
        return proList;

    }
}
