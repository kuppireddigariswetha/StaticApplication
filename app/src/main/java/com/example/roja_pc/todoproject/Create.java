package com.example.roja_pc.todoproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pc on 5/4/2017.
 */
public class Create extends Activity {
    ListView listview;
    int i,j;
    String rangee;
    Button Addbutton,Cancel,okay;
    EditText GetValue;
    String[] ListElements = new String[] {
            "0-500"
    };
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listitem);

        listview = (ListView) findViewById(R.id.listView1);
        Addbutton = (Button) findViewById(R.id.button1);
        GetValue = (EditText)findViewById(R.id.editText1);
        Cancel=(Button)findViewById(R.id.button2);
        okay=(Button)findViewById(R.id.button3);
        final List<String> ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (Create.this, android.R.layout.simple_list_item_1, ListElementsArrayList);

        listview.setAdapter(adapter);

        Addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str=GetValue.getText().toString();
                rangee=str;
                if(str.equals(""))
                {
                    Log.d("value--51","hiiiiiiiiiii");
                    return;
                }
                if(!str.isEmpty())
                {
                    try
                    {
                        String[] separated = str.split("-");
                        String index1=separated[0];
                        Log.d("index1",index1);
                        i= Integer.parseInt(index1);
                        String index2=separated[1];
                        Log.d("index2",index2);
                        j= Integer.parseInt(index2);
                        if(!str.isEmpty()&&i>=2000&&j>=i+500)
                        {
                            ListElementsArrayList.add(GetValue.getText().toString());
                            adapter.notifyDataSetChanged();
                            GetValue.setText("");
                            GetValue.setVisibility(View.INVISIBLE);
                            okay.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Toast toast= Toast.makeText(getApplicationContext(),"enter a valid range value", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                        Toast toast= Toast.makeText(getApplicationContext(),"enter a valid range value", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                    }
                }
                else {
                    Toast toast= Toast.makeText(getApplicationContext(),"enter a valid range value", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetValue.setText("");
               GetValue.setVisibility(View.VISIBLE);
            }
        });
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent();
                i.putExtra("MESSAGE",rangee);
                Log.d("message--109",rangee);
                setResult(2,i);
                finish();
            }
        });

    }
    public void onBackPressed()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Create.this);
        alertDialog.setMessage("Do you want to go back");
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent i=new Intent(getApplicationContext(),MedNoteMain.class);
                startActivity(i);
            }
        });
        alertDialog.show();
    }
}

