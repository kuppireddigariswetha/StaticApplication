package com.example.roja_pc.todoproject;

/**
 * Created by Roja-PC on 11-01-2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;


public class XlsxCon {

    String TAG = "DBAdapter";
    public static final String Tablename = "MyTable1";
    public static final String id = "_id";// 0 integer
    public static final String StudentName = "Studentname";// 1 text(String)
    public static final String Uniqueid = "Uniqueid";// 2 integer
    public static final String FatherName = "Fathername";// 3 date(String)
    public static final String School = "School";// 1 text(String)
    public static final String District = "District";// 2 integer
    public static final String Dob = "Dob";// 3 date(String)
    public static final String Class = "Class";// 1 text(String)
    public static final String Section = "section";// 2 integer
    public static final String Mobile = "mobile";// 3 date(String)
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public XlsxCon(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() {
        if (null == db || !db.isOpen()) {
            try {
                db = dbHelper.getWritableDatabase();

            } catch (SQLiteException sqLiteException) {

            }
        }

    }

    public void close() {
        if (db != null) {

            db.close();
        }
    }

    public int insert(String table, ContentValues values) {
        return (int) db.insert(table, null, values);

    }

    public void delete() {
        db.execSQL("delete from " + Tablename);

    }

    public Cursor getAllRow(String table) {

        return db.query(table, null, null, null, null, null, id);

    }

    private class DBHelper extends SQLiteOpenHelper {

        private static final int VERSION = 1;
        private static final String DB_NAME = "MyDB1.db";

        public DBHelper(Context context) {

            super(context, DB_NAME, null, VERSION);

        }
        @Override

        public void onCreate(SQLiteDatabase db) {

            String create_sql = "CREATE TABLE IF NOT EXISTS " + Tablename + "("
                    + id + " INTEGER PRIMARY KEY AUTOINCREMENT ,"  + StudentName + " TEXT ," + Uniqueid + " TEXT ,"
                    + FatherName + " TEXT ," + School + " TEXT ,"+ District + " TEXT ," + Dob + " TEXT ," + Class + " TEXT ," + Section + " TEXT ," + Mobile + " TEXT ," + ")";

            db.execSQL(create_sql);

        }

        @Override

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + Tablename);

        }

    }



    public ArrayList<HashMap<String, String>> getProducts() {

        ArrayList<HashMap<String, String>> prolist;

        prolist = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT  * FROM " + Tablename;

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                HashMap<String, String> map = new HashMap<String, String>();

                map.put(StudentName, cursor.getString(1));

                map.put(Uniqueid, cursor.getString(2));

                map.put(FatherName, cursor.getString(3));

                map.put(School, cursor.getString(4));

                map.put(District, cursor.getString(5));

                map.put(Dob, cursor.getString(6));

                map.put(Class, cursor.getString(7));

                map.put(Section, cursor.getString(8));

                map.put(Mobile, cursor.getString(9));

                prolist.add(map);

            } while (cursor.moveToNext());
            }

        return prolist;

    }

}
