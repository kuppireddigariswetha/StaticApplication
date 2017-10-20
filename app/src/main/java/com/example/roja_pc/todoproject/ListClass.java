package com.example.roja_pc.todoproject;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by pc on 4/19/2017.
 */

public class ListClass extends Activity {

    private DBManager dbManager;
    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{DatabaseHelper._ID, DatabaseHelper.TODO_UID, DatabaseHelper.TODO_NAME, DatabaseHelper.TODO_FATHERNAME,
            DatabaseHelper.TODO_SCHOOLNAME, DatabaseHelper.TODO_DISTRICT, DatabaseHelper.TODO_DATEOFBIRTH,
            DatabaseHelper.TODO_MOBILE, DatabaseHelper.TODO_ADNO, DatabaseHelper.TODO_SECTION, DatabaseHelper.TODO_CLASS, DatabaseHelper.TODO_DATEOFEXAM,
            DatabaseHelper.TODO_HEIGHT,DatabaseHelper.TODO_WEIGHT, DatabaseHelper.TODO_PULSE, DatabaseHelper.TODO_BP,
            DatabaseHelper.TODO_HB, DatabaseHelper.TODO_BLOODGROUP, DatabaseHelper.TODO_DESCRIPTION1, DatabaseHelper.TODO_ADVICE,DatabaseHelper.TODO_WITHOUTR,
            DatabaseHelper.TODO_WITHOUTL, DatabaseHelper.TODO_WITHR, DatabaseHelper.TODO_WITHL, DatabaseHelper.TODO_RG1, DatabaseHelper.TODO_RG2, DatabaseHelper.TODO_DESCRIPTION2,
            DatabaseHelper.TODO_RG3,DatabaseHelper.TODO_RG4, DatabaseHelper.TODO_DESCRIPTION3, DatabaseHelper.TODO_RG5,
            DatabaseHelper.TODO_RG6, DatabaseHelper.TODO_RG7, DatabaseHelper.TODO_RG8, DatabaseHelper.TODO_RG9, DatabaseHelper.TODO_DESCRIPTION4};

    final int[] to = new int[]{R.id.id, R.id.title, R.id.desc};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

    }
}
