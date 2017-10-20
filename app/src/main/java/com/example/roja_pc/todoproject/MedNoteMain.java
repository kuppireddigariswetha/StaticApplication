package com.example.roja_pc.todoproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MedNoteMain extends Activity implements View.OnClickListener , AdapterView.OnItemSelectedListener{

    EditText ed1,ed2;
    Button btn1,btn2;
    CheckBox cb1;
    TextView tx1;
    SharedPreferences loginpreferences;
    SharedPreferences.Editor loginprefseditor;
    SharedPreferences.Editor editor;
    String selected1,range;
    ArrayList<String> list = new ArrayList<String>(){{
        add("0-500");
        add("500-1000");
        add("1000-1500");
        add("1500-2000");
    }};
    /** Declaring an ArrayAdapter to set items to ListView */
    ArrayAdapter<String> adapter;
    Button btn4,btn5,btn6;
    Spinner spp1,spp2;
    private Boolean saveLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mednote_activity);

        loginpreferences=getSharedPreferences("login prefs",MODE_PRIVATE);
        loginprefseditor=loginpreferences.edit();

        SharedPreferences   pref=getApplicationContext().getSharedPreferences("options",MODE_PRIVATE);
        editor=pref.edit();

        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        cb1=(CheckBox)findViewById(R.id.remember);
        btn1=(Button)findViewById(R.id.login);
        btn2=(Button)findViewById(R.id.cancel);
        tx1=(TextView)findViewById(R.id.forgot_pwd);

        saveLogin = loginpreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            ed1.setText(loginpreferences.getString("username", ""));
            ed2.setText(loginpreferences.getString("password", ""));
            cb1.setChecked(true);
        }
        spp1=(Spinner) findViewById(R.id.spii);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.School_array,R.layout.spinner_school);
        adapter2.setDropDownViewResource(R.layout.spinner_school);
        spp1.setAdapter(adapter2);
        spp1.setOnItemSelectedListener(this);

        cb1.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        tx1.setOnClickListener(this);

    }
    public void onBackPressed()
    {

        moveTaskToBack(true);
    }
    public void onClick(View v)
    {
        if (cb1.isChecked()) {
        loginprefseditor.putBoolean("saveLogin", true);
        loginprefseditor.putString("username", ed1.getText().toString());
        loginprefseditor.putString("password", ed2.getText().toString());
        loginprefseditor.commit();
    } else {
        loginprefseditor.clear();
        loginprefseditor.commit();
    }

        switch (v.getId())
        {

            case R.id.login:
                if((ed1.getText().toString().equals("medusersw1@gmail.com")) &&(ed2.getText().toString().equals("12345678"))&&selected1.equals("tswreis") ||
                        (ed1.getText().toString().equals("medusermw1@gmail.com")) &&(ed2.getText().toString().equals("12345678")) &&(selected1.equals("tmreis"))||
                                (ed1.getText().toString().equals("medusertw1@gmail.com")) &&(ed2.getText().toString().equals("12345678")) &&(selected1.equals("ttwreis"))||
                        (ed1.getText().toString().equals("schoolhealth.ameya@gmail.com")) &&(ed2.getText().toString().equals("tlstec123")) &&(selected1.equals("ameya schools")))
                {
                    showRange();
                }
               else
                {
                    Toast toast=Toast.makeText(this,"Your not the valid user",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
                break;
            case R.id.cancel:
                moveTaskToBack(true);
                break;
            case R.id.range3:
                Intent intent=new Intent(MedNoteMain.this,Create.class);
                btn4.setVisibility(View.INVISIBLE);
                btn6.setVisibility(View.VISIBLE);
                startActivityForResult(intent, 2);
                break;
            case R.id.range4:
                String s=ed1.getText().toString();
                Intent i = new Intent(getApplicationContext(), TodoListActivity.class);
                editor.putString("one",s);
                editor.putString("two",selected1);
                editor.putString("length",range);
                editor.commit();
                startActivity(i);
                break;
            case R.id.range5:
                list.remove(4);
                adapter.notifyDataSetChanged();
                Intent intent1=new Intent(MedNoteMain.this,Create.class);
                btn4.setVisibility(View.INVISIBLE);
                btn6.setVisibility(View.VISIBLE);
                startActivityForResult(intent1, 2);
                break;

        }

    }

    private void showRange() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.range_id);

        /** Defining the ArrayAdapter to set items to Spinner Widget */
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        btn4=(Button)dialog.findViewById(R.id.range3);
        btn4.setOnClickListener(this);
        btn5=(Button)dialog.findViewById(R.id.range4);
        btn5.setOnClickListener(this);
        btn6=(Button)dialog.findViewById(R.id.range5);
        btn6.setOnClickListener(this);
        /** Getting a reference to Spinner object of the resource activity_main */
        Spinner spinner = (Spinner) dialog. findViewById(R.id.range2);
        /** Setting the adapter to the ListView */
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        /** Adding radio buttons for the spinner items */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dialog.show();
    }


    public void savePreferences(String key, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }

    private void savePreferences1(String key, String value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }
    private void clearPreference() {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.commit();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId())
        {
            case R.id.spii:
                String selected11=parent.getItemAtPosition(position).toString();
                selected1=selected11;
                Log.d("school",selected1.toString());
            case R.id.range2:
                String selected12=parent.getItemAtPosition(position).toString();
                range=selected12;
                Log.d("poppppppp",parent.getItemAtPosition(position).toString());
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            String message=data.getStringExtra("MESSAGE");
            list.add(message);
            Log.d("message",message);
            adapter.notifyDataSetChanged();   }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
