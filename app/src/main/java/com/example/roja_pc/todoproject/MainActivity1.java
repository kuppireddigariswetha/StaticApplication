package com.example.roja_pc.todoproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity1 extends ListActivity {
    TextView lbl;
    DBController controller = new DBController(this);
    Button btnimport,dataretrive;
    ListView lv;
    public static int ListLength;
    final Context context = this;
    ListAdapter adapter;
    ArrayList<HashMap<String, String>> myList;
    public static final int requestcode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        lbl = (TextView) findViewById(R.id.txtresulttext);
        btnimport = (Button) findViewById(R.id.btnupload);
        dataretrive=(Button) findViewById(R.id.btnn);
        lv = getListView();
        btnimport.setOnClickListener(new View.OnClickListener() {



            @Override

            public void onClick(View v) {
                Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
                fileintent.setType("gagt/sdf");
                try {
                    startActivityForResult(fileintent, requestcode);
                } catch (ActivityNotFoundException e) {
                    lbl.setText("No activity can handle picking a file. Showing alternatives.");
                }
            }

        });
        myList= controller.getAllProducts();
        Log.d("length===65", String.valueOf(myList.size()));

        if (myList.size() != 0) {
            ListView lv = getListView();
            ListAdapter adapter = new SimpleAdapter(MainActivity1.this, myList,
                    R.layout.v, new String[]{"StudentName", "Uniqueid", "Fathername", "School", "District", "Dob", "Class", "Section", "Mobile"}, new int[]{
                    R.id.txtSN, R.id.txtUID, R.id.txtFN, R.id.txtSCHOOL, R.id.txtDISTRICT, R.id.txtDOB, R.id.txtCLASS, R.id.txtSECTION, R.id.txtMOBILE });
            setListAdapter(adapter);
            lbl.setText("");

        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;

        switch (requestCode) {
            case requestcode:

                String filepath = data.getData().getPath();
                controller = new DBController(getApplicationContext());
                SQLiteDatabase db = controller.getWritableDatabase();
                String tableName = "proinfo";
                db.execSQL("delete from " + tableName);

                try {
                    if (resultCode == RESULT_OK) {
//Log.v("try==94","inside");
                        try {
                          //  Log.v("try==96","inside");
                            FileReader file = new FileReader(filepath);
                           // Log.v("try==98","inside");
                            BufferedReader buffer = new BufferedReader(file);
                           // Log.v("try==100","inside");
                            ContentValues contentValues = new ContentValues();
                            String line = "";
                            db.beginTransaction();
                            while ((line = buffer.readLine()) != null) {
                                String[] str = line.split(",", 9);
                               // Log.v("try==106","inside");
                                String StudentName = str[0].toString();
                                Log.v("try==108",StudentName);
                                String Uniqueid = str[1].toString();
                                Log.v("try==110",Uniqueid);
                                String Fathername = str[2].toString();
                                Log.v("try==112",Fathername);
                                String School = str[3].toString();
                                String District = str[4].toString();
                                String Dob = str[5].toString();
                                String Class  = str[6].toString();
                                String Section = str[7].toString();
                                String Mobile = str[8].toString();
                                contentValues.put("Studentname", StudentName);
                                contentValues.put("Uniqueid", Uniqueid);
                                contentValues.put("Fathername", Fathername);
                                contentValues.put("School", School);
                                contentValues.put("District", District);
                                contentValues.put("Dob", Dob);
                                contentValues.put("Class", Class);
                                contentValues.put("Section", Section);
                                contentValues.put("Mobile", Mobile);
                                Log.v("CV Length==126",String.valueOf(contentValues.size()));
                                db.insert(tableName, null, contentValues);
                               // lbl.setText("Successfully Updated Database.");
                            }
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            db.close();

                        } catch (IOException e) {
                            if (db.inTransaction())
                                db.endTransaction();
                           // Log.v("try==135","inside");
                            Dialog d = new Dialog(this);
                            d.setTitle(e.getMessage().toString() + "first");
                            d.show();
                            db.endTransaction();

                        }
                    } else {
                        if (db.inTransaction())
                            db.endTransaction();
                        Dialog d = new Dialog(this);
                        d.setTitle("Only CSV files allowed");
                        d.show();
                    }

                } catch (Exception ex) {
                    if (db.inTransaction())
                        db.endTransaction();
                         Dialog d = new Dialog(this);
                    d.setTitle(ex.getMessage().toString() + "second");
                    d.show();
                    //db.endTransaction();
                    db.close();
                }

        }
        myList= controller.getAllProducts();



        Log.d("mylist---258",myList.toString());
        ListLength=myList.size();

        if (myList.size() != 0) {

            ListView lv = getListView();
            Log.d("mylist---262",lv.toString());
            ListAdapter adapter = new SimpleAdapter(MainActivity1.this, myList,

                    R.layout.v, new String[]{"StudentName", "Uniqueid", "Fathername", "School", "District", "Dob", "Class", "Section", "Mobile"}, new int[]{

                    R.id.txtSN, R.id.txtUID, R.id.txtFN, R.id.txtSCHOOL, R.id.txtDISTRICT, R.id.txtDOB, R.id.txtCLASS, R.id.txtSECTION, R.id.txtMOBILE});
            Log.d("mylist---268",adapter.toString());
            setListAdapter(adapter);
            //lv.setAdapter(adapter);
            Log.d("mylist---271","ADAPTERRRRRRRRR");
           /* lbl.setText("Data Imported");*/
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
            builder.setTitle("Do You Want Exit");
            builder.setMessage("Data Imported Successfully ....");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   Intent i=new Intent(MainActivity1.this,TodoListActivity.class);
                    startActivity(i);
                }
            });

            builder.setNegativeButton("Cancel", null);
            builder.show();
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
